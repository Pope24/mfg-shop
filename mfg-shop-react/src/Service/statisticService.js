import axios from "axios";

export const getTop10ProductSeller = async (token) => {
  const headers = {
    Authorization: `Bearer ${token}`,
  };
  try {
    const result = await axios.get(
      "http://localhost:8080/api/admin/statistic/top-product",
      { headers }
    );
    return result.data;
  } catch (e) {
    console.log(e);
  }
};
export const getTop10CustomerBuyer = async (token) => {
  const headers = {
    Authorization: `Bearer ${token}`,
  };
  try {
    const result = await axios.get(
      "http://localhost:8080/api/admin/statistic/top-customer",
      { headers }
    );
    return result.data;
  } catch (e) {
    console.log(e);
  }
};
