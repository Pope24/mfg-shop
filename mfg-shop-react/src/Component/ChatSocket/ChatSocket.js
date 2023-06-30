import { useEffect, useState } from "react";
import { over } from "stompjs";
import SockJS from "sockjs-client";
import styles from "./ChatSocket.module.css";
var stompClient = null;
function ChatSocket() {
  const [publicChat, setPublicChat] = useState([]);
  const [privateChat, setPrivateChat] = useState(new Map());
  const [tab, setTab] = useState("CHATROOM");
  const [userData, setUserData] = useState({
    username: "",
    receivername: "",
    connected: false,
    message: "",
  });
  useEffect(() => {
    console.log(userData);
  }, [userData]);
  const handleUserName = (e) => {
    setUserData({ ...userData, username: e.target.value });
    console.log(userData);
  };
  const handleMessage = (e) => {
    setUserData({ ...userData, message: e.target.value });
  };
  const registerUser = () => {
    let sock = new SockJS("http://localhost:8080/api/public/ws");
    stompClient = over(sock);
    stompClient.connect({}, onConnected, onError);
  };
  const onConnected = () => {
    setUserData({ ...userData, connected: true });
    stompClient.subscribe("/chatroom/public", onPublicMessageReceived);
    stompClient.subscribe(
      "/user/" + userData.username + "/private",
      onPrivateMessageReceived
    );
    userJoin();
  };
  const userJoin = () => {
    let chatMessage = {
      senderName: userData.username,
      statusMessage: "JOIN",
    };
    stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
  };
  const onError = (e) => {
    console.log(e);
  };
  const onPublicMessageReceived = (payload) => {
    console.log(payload);
    let payloadData = JSON.parse(payload.body);
    switch (payloadData.statusMessage) {
      case "JOIN":
        if (!privateChat.get(payloadData.senderName)) {
          privateChat.set(payloadData.senderName, []);
          setPrivateChat(new Map(privateChat));
        }
        break;
      case "MESSAGE":
        publicChat.push(payloadData);
        setPublicChat([...publicChat]);
        break;
      default:
    }
  };
  const onPrivateMessageReceived = (payload) => {
    let payloadData = JSON.parse(payload);
    if (privateChat.get(payloadData.senderName)) {
      privateChat.get(payloadData.senderName).push(payloadData);
      setPrivateChat(new Map(privateChat));
    } else {
      let list = [];
      list.push(payloadData);
      privateChat.set(payloadData.senderName, list);
      setPrivateChat(new Map(privateChat));
    }
  };
  const handleSendPublicMessage = () => {
    if (stompClient) {
      let chatMessage = {
        senderName: userData.username,
        message: userData.message,
        statusMessage: "MESSAGE",
      };
      stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
      setUserData({ ...userData, message: "" });
    }
  };
  const handleSendPrivateMessage = () => {
    if (stompClient) {
      let chatMessage = {
        senderName: userData.username,
        receivername: tab,
        message: userData.message,
        statusMessage: "MESSAGE",
      };
      if (userData.username !== tab) {
        privateChat.get(tab).push(chatMessage);
        setPrivateChat(new Map(privateChat));
      }
      stompClient.send("/app/private-message", {}, JSON.stringify(chatMessage));
      setUserData({ ...userData, message: "" });
    }
  };
  return (
    <>
      <div className="container">
        {userData.connected ? (
          <div className={styles.chat_box}>
            <div className={styles.member_list}>
              <ul>
                <li
                  onClick={() => {
                    setTab("CHATROOM");
                  }}
                  className={`${styles.member} ${
                    tab === "CHATROOM" && styles.active
                  }`}
                >
                  Chatroom
                </li>
                {[...privateChat.keys()].map((name, index) => {
                  return (
                    <li
                      onClick={() => {
                        setTab(name);
                      }}
                      className={`${styles.member} ${
                        tab === name && styles.active
                      }`}
                      key={index}
                    >
                      {name}
                    </li>
                  );
                })}
              </ul>
            </div>
            {tab === "CHATROOM" && (
              <div className={styles.chat_content}>
                <ul className={styles.chat_message}>
                  {publicChat.map((chat, index) => {
                    return (
                      <li className={styles.message} key={index}>
                        {chat.senderName !== userData.username && (
                          <div className={styles.avatar}>{chat.senderName}</div>
                        )}
                        <div className={styles.message_data}>
                          {chat.message}
                        </div>
                        {chat.senderName === userData.username && (
                          <div className={styles.avatar_self}>
                            {chat.senderName}
                          </div>
                        )}
                      </li>
                    );
                  })}
                </ul>
                <div className={styles.send_message}>
                  <input
                    type="text"
                    className={styles.input_message}
                    placeholder="Nhập đoạn chat vào đây"
                    value={userData.message}
                    onChange={(e) => {
                      handleMessage(e);
                    }}
                  />
                  <button
                    type="button"
                    className={styles.send_button}
                    onClick={handleSendPublicMessage}
                  >
                    Gửi
                  </button>
                </div>
              </div>
            )}
            {tab !== "CHATROOM" && (
              <div className={styles.chat_content}>
                <ul className={styles.chat_message}>
                  {[...privateChat.get(tab)].map((chat, index) => {
                    return (
                      <li className={styles.message} key={index}>
                        {chat.senderName !== userData.username && (
                          <div className={styles.avatar}>{chat.senderName}</div>
                        )}
                        <div className={styles.message_data}>
                          {chat.message}
                        </div>
                        {chat.senderName === userData.username && (
                          <div className={styles.avatar_self}>
                            {chat.senderName}
                          </div>
                        )}
                      </li>
                    );
                  })}
                </ul>
                <div className={styles.send_message}>
                  <input
                    type="text"
                    className={styles.input_message}
                    placeholder={`Nhập đoạn chat cho ${tab} vào đây`}
                    value={userData.message}
                    onChange={(e) => {
                      handleMessage(e);
                    }}
                  />
                  <button
                    type="button"
                    className={styles.send_button}
                    onClick={handleSendPrivateMessage}
                  >
                    Gửi
                  </button>
                </div>
              </div>
            )}
          </div>
        ) : (
          <div className={styles.register}>
            <input
              id="username"
              placeholder="Nhập tên của bạn để tiếp tục chat"
              value={userData.username}
              onChange={(e) => {
                handleUserName(e);
              }}
            />
            <button type="button" onClick={registerUser}>
              Connect
            </button>
          </div>
        )}
      </div>
    </>
  );
}

export default ChatSocket;
