import logo from "./logo.svg";
import "./App.css";
import { Route, Routes } from "react-router-dom";
import Home from "./Component/Home/Home";
import Login from "./Component/Login/Login";
import DetailProduct from "./Component/DetailProduct/DetailProduct";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/product/:id" element={<DetailProduct />} />
      <Route path="/login" element={<Login />} />
    </Routes>
  );
}

export default App;
