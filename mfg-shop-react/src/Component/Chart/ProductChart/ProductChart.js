import styles from "./ProductChart.module.css";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  Tooltip,
  ResponsiveContainer,
} from "recharts";
import { useState } from "react";
import { useEffect } from "react";
import { getTop10ProductSeller } from "../../../Service/statisticService";

function ProductChart() {
  const account = JSON.parse(localStorage.getItem("account"));
  const [topProduct, setTopProduct] = useState(null);
  useEffect(() => {
    const fetchApiToGetTopProduct = async () => {
      const data = await getTop10ProductSeller(account.token);
      setTopProduct(data);
    };
    fetchApiToGetTopProduct();
  }, []);
  return (
    <>
      <div className="container mb-5">
        {topProduct && (
          <>
            <ResponsiveContainer width="100%" height={500}>
              <BarChart data={topProduct}>
                <XAxis
                  dataKey="product.nameProduct"
                  tick={{ fontSize: 10 }}
                  angle={-10}
                  fill="#888fab"
                  tickMargin={10}
                  textAnchor="middle"
                />
                <YAxis
                  dataKey="amount"
                  tick={{ fontSize: 12 }}
                  fill="#888fab"
                />
                <Tooltip />
                <Bar fill="#888fab" />
                <Bar dataKey="amount" fill="#888fab" />
              </BarChart>
            </ResponsiveContainer>
          </>
        )}
      </div>
    </>
  );
}

export default ProductChart;
