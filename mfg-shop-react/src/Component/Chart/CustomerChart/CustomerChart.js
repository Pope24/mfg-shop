import styles from "./CustomerChart.module.css";
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
import {
  getTop10CustomerBuyer,
  getTop10ProductSeller,
} from "../../../Service/statisticService";

function CustomerChart() {
  const account = JSON.parse(localStorage.getItem("account"));
  const [topCustomer, setTopCustomer] = useState(null);
  useEffect(() => {
    const fetchApiToGetTopCustomer = async () => {
      const data = await getTop10CustomerBuyer(account.token);
      const processedData = data.map((item) => ({
        name: item.employee
          ? item.employee.nameEmployee
          : item.customer.nameCustomer,
        totalMoney: item.totalMoney,
      }));
      setTopCustomer(processedData);
    };
    fetchApiToGetTopCustomer();
  }, []);
  const formatMoney = (value) => {
    return value.toLocaleString("it-IT", {
      style: "currency",
      currency: "VND",
    });
  };
  const formatMoneyYAxis = (value) => {
    if (value == 0) return 0;
    return value / 1000000 + " triệu";
  };
  return (
    <>
      <div className="container mb-5">
        {topCustomer && (
          <>
            <ResponsiveContainer width="100%" height={500}>
              <BarChart data={topCustomer}>
                <XAxis
                  dataKey="name"
                  tick={{ fontSize: 10 }}
                  angle={-10}
                  fill="#888fab"
                  tickMargin={10}
                  textAnchor="middle"
                />
                <YAxis
                  dataKey="totalMoney"
                  tick={{ fontSize: 12 }}
                  fill="#888fab"
                  tickFormatter={formatMoneyYAxis}
                />
                <Tooltip formatter={formatMoney} />
                <Bar fill="#888fab" />
                <Bar dataKey="totalMoney" fill="#888fab" />
              </BarChart>
            </ResponsiveContainer>
          </>
        )}
      </div>
    </>
  );
}

export default CustomerChart;
