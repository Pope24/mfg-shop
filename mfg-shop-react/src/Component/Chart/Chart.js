import { useState } from "react";
import styles from "./Chart.module.css";
import Footer from "../Common/Footer/Footer";
import Header from "../Common/Header/Header";
import ProductChart from "./ProductChart/ProductChart";
import CustomerChart from "./CustomerChart/CustomerChart";
import { Backdrop, CircularProgress } from "@mui/material";

function Chart() {
  const [isToggle, setIsToggle] = useState(true);
  const [open, setOpen] = useState(false);
  const handleClose = () => {
    setOpen(false);
  };
  const handleOpen = () => {
    setOpen(true);
  };
  return (
    <>
      <Header />
      <div className="container" style={{ marginBottom: 100 }}>
        {isToggle ? (
          <h4 className="text-center fw-bold p-4">
            Top 10 sản phẩm bán chạy nhất tại MFG Shop
          </h4>
        ) : (
          <h4 className="text-center fw-bold p-4">
            Top 10 khách hàng mua hàng nhiều nhất tại MFG Shop
          </h4>
        )}
        <div className="d-flex justify-content-end">
          <button
            className={`${styles.btn} ${isToggle ? styles.active : ""} me-2`}
            onClick={() => {
              if (isToggle === false) {
                handleOpen();
                setTimeout(() => {
                  setIsToggle(true);
                  handleClose();
                }, 3000);
              }
            }}
          >
            Top sản phẩm
          </button>
          <button
            className={`${styles.btn}  ${!isToggle ? styles.active : ""}`}
            onClick={() => {
              if (isToggle === true) {
                handleOpen();
                setTimeout(() => {
                  setIsToggle(false);
                  handleClose();
                }, 3000);
              }
            }}
          >
            Top khách hàng
          </button>
        </div>
        {isToggle ? <ProductChart /> : <CustomerChart />}
      </div>
      <Footer />
      <div>
        <Backdrop
          sx={{ color: "#fff", zIndex: (theme) => theme.zIndex.drawer + 1 }}
          open={open}
        >
          <CircularProgress color="inherit" />
        </Backdrop>
      </div>
    </>
  );
}

export default Chart;
