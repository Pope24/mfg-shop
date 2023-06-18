import styles from "./Header.module.css";
import { IMAGES } from "../../Assets";
import {
  SearchOutlined,
  ShoppingCartOutlined,
  UserOutlined,
} from "@ant-design/icons";
import { Avatar } from "antd";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
function Header({ onSearchClick }) {
  const [value, setValue] = useState("");
  const navigate = useNavigate();
  const handleInputChange = (event) => {
    setValue(event.target.value);
  };

  const handleButtonClick = () => {
    onSearchClick(value);
  };
  return (
    <>
      <div className={styles.header}>
        <div className="container h-100 d-flex justify-content-between align-items-center">
          <div
            className={`${styles.left_header} + h-100 d-flex align-items-center`}
          >
            <img src={IMAGES.LOGO} className="h-100" alt="" />
            <h1 className={styles.name_company}>MFG OFFICIAL ONLINE STORE</h1>
          </div>
          <div className="d-flex justify-content-end align-items-center">
            <div className={styles.search_box}>
              <button
                className={`${styles.btn_search}  d-flex`}
                onClick={handleButtonClick}
              >
                <SearchOutlined style={{ fontSize: 36, color: "#a6a6a6" }} />
              </button>
              <input
                type="text"
                className={styles.input_search}
                placeholder="Tìm kiếm sản phẩm..."
                value={value}
                onChange={handleInputChange}
                onBlur={handleButtonClick}
              />
            </div>
            <ShoppingCartOutlined
              className="ms-5"
              style={{ fontSize: 40, color: "#a6a6a6" }}
            />
            <Avatar
              size="large"
              className="ms-5"
              icon={<UserOutlined className="m-auto" />}
            />
            <img src={IMAGES.ICON_VIETNAM} className="ms-5" alt="" />
          </div>
        </div>
      </div>
      <div
        className={`${styles.navbar} + d-flex justify-content-center align-items-center`}
      >
        <div className="container">
          <ul
            className={`${styles.menu_navbar} + d-flex justify-content-between align-items-center list-unstyled w-100 m-0`}
          >
            <li>Best seller</li>
            <li
              onClick={() => {
                navigate("/");
              }}
            >
              Sản phẩm
            </li>
            <li>Bộ sưu tập</li>
            <li>Basic line</li>
            <li>Make your style</li>
            <li>Outlet style</li>
            <li>Bad MFG club</li>
          </ul>
        </div>
      </div>
    </>
  );
}

export default Header;
