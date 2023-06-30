import styles from "./Header.module.css";
import Avatar from "@mui/material/Avatar";
import { IMAGES } from "../../Assets";
import { SearchOutlined, ShoppingCartOutlined } from "@ant-design/icons";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { Tooltip } from "react-tooltip";
import { getAllProduct } from "../../../Service/productService";
function Header() {
  const [roles, setRoles] = useState(null);
  const [account, setAccount] = useState(null);
  const [productSearched, setProductSearched] = useState(null);
  const [searchAndPage, setSearchAndPage] = useState({
    search: "",
    typeProduct: 0,
  });
  useEffect(() => {
    const fetchApiToGetProducts = async () => {
      const result = await getAllProduct(searchAndPage);
      setProductSearched(result.content);
    };
    fetchApiToGetProducts();
  }, [searchAndPage]);
  const handleSearchChange = (e) => {
    setSearchAndPage((prev) => ({ ...prev, search: e.target.value }));
  };
  const navigate = useNavigate();
  useEffect(() => {
    const account = JSON.parse(localStorage.getItem("account"));
    if (account) {
      let roleArr = [];
      for (let i = 0; i < account.roles.length; i++) {
        roleArr.push(account.roles[i].authority);
      }
      setAccount(account);
      setRoles(roleArr);
    }
  }, []);
  const handleBlurInputSearch = () => {
    document.getElementById("productSeached").style.opacity = 0;
  };
  const handleFocusInputSearch = () => {
    document.getElementById("productSeached").style.opacity = 1;
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
              <div className="d-flex align-items-center">
                <button className={`${styles.btn_search}  d-flex`}>
                  <SearchOutlined style={{ fontSize: 36, color: "#a6a6a6" }} />
                </button>
                <input
                  type="text"
                  className={styles.input_search}
                  placeholder="Tìm kiếm sản phẩm..."
                  onChange={handleSearchChange}
                  onBlur={handleBlurInputSearch}
                  onFocus={handleFocusInputSearch}
                />
              </div>
              <div
                id="productSeached"
                style={{
                  position: "absolute",
                  top: 50,
                  backgroundColor: "chocolate",
                  zIndex: 10000,
                  width: "100%",
                }}
              >
                {productSearched &&
                productSearched.length > 0 &&
                searchAndPage.search.trim() !== "" ? (
                  productSearched.map((product, index) => {
                    return (
                      <div
                        key={index}
                        className="d-flex justify-content-between align-items-center"
                        style={{ border: "1px solid", height: 50 }}
                        onClick={() => {
                          navigate("/product/" + product.idProduct);
                        }}
                      >
                        <div className="col-2">
                          <img
                            className="w-50"
                            src={product.imgProducts[0].pathImg}
                            alt="IMG"
                          />
                        </div>
                        <p
                          className="m-0"
                          style={{ fontSize: 14, fontWeight: 600 }}
                        >
                          {product.nameProduct}
                        </p>
                        <p
                          className="m-0"
                          style={{ fontSize: 14, fontWeight: 600 }}
                        >
                          {product.currCost.toLocaleString("it-IT", {
                            style: "currency",
                            currency: "VND",
                          })}
                        </p>
                      </div>
                    );
                  })
                ) : productSearched &&
                  productSearched.length === 0 &&
                  searchAndPage.search.trim() !== "" ? (
                  <div
                    style={{ border: "1px solid", height: 50 }}
                    className="w-100 d-flex justify-content-center align-items-center"
                  >
                    <p className="m-0">
                      Không tìm thấy sản phẩm "{searchAndPage.search}"
                    </p>
                  </div>
                ) : (
                  <></>
                )}
              </div>
            </div>
            <ShoppingCartOutlined
              className="ms-5"
              style={{ fontSize: 44, color: "#a6a6a6", cursor: "pointer" }}
              onClick={() => {
                navigate("/shopping-cart");
              }}
            />
            <Avatar className={`${styles.avatar} ms-5`} id="avatar">
              {(account && account.username[0].toUpperCase()) || "NG"}
            </Avatar>
            <Tooltip
              anchorSelect="#avatar"
              style={{ zIndex: 10000, fontSize: 16 }}
              clickable
            >
              {roles && roles.includes("ADMIN") && (
                <div className={`${styles.author_account}`}>
                  <button
                    onClick={() => {
                      navigate("/login");
                    }}
                    className="bg-transparent border-0 text-light"
                  >
                    Quản lý nhân viên
                  </button>
                </div>
              )}
              {roles && roles.includes("ADMIN") && (
                <div
                  className={`${styles.author_account} ${styles.border_author}`}
                >
                  <button
                    onClick={() => {
                      navigate("/statistic");
                    }}
                    className="bg-transparent border-0 text-light"
                  >
                    Thống kê cửa hàng
                  </button>
                </div>
              )}
              {roles &&
                (roles.includes("ADMIN") || roles.includes("EMPLOYEE")) && (
                  <div className={`${styles.author_account}`}>
                    <button
                      onClick={() => {
                        navigate("/chatting");
                      }}
                      className="bg-transparent border-0 text-light"
                    >
                      Tư vấn mua hàng
                    </button>
                  </div>
                )}
              {roles &&
                (roles.includes("ADMIN") || roles.includes("EMPLOYEE")) && (
                  <div className={`${styles.author_account}`}>
                    <button
                      onClick={() => {
                        navigate("/management-order");
                      }}
                      className="bg-transparent border-0 text-light"
                    >
                      Quản lý đơn hàng
                    </button>
                  </div>
                )}
              {roles &&
                (roles.includes("ADMIN") || roles.includes("EMPLOYEE")) && (
                  <div
                    className={`${styles.author_account} ${styles.border_author}`}
                  >
                    <button
                      onClick={() => {
                        navigate("/login");
                      }}
                      className="bg-transparent border-0 text-light"
                    >
                      Danh sách khách hàng
                    </button>
                  </div>
                )}
              {account && (
                <div className={`${styles.author_account}`}>
                  <button
                    onClick={() => {
                      navigate("/history");
                    }}
                    className="bg-transparent border-0 text-light"
                  >
                    Lịch sử mua hàng
                  </button>
                </div>
              )}
              {account && (
                <div className={`${styles.author_account}`}>
                  <button
                    onClick={() => {
                      navigate("/login");
                    }}
                    className="bg-transparent border-0 text-light"
                  >
                    Quản lý tài khoản
                  </button>
                </div>
              )}
              {account && (
                <div className={`${styles.author_account}`}>
                  <button
                    onClick={() => {
                      localStorage.removeItem("account");
                      window.location = "/";
                    }}
                    className="bg-transparent border-0 text-light"
                  >
                    Đăng xuất
                  </button>
                </div>
              )}
              {!account && (
                <button
                  onClick={() => {
                    navigate("/login");
                  }}
                  className="bg-transparent border-0 text-light"
                >
                  Đăng nhập
                </button>
              )}
            </Tooltip>
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
