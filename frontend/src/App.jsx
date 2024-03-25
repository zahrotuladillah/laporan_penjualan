import { useEffect, useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import axios from "axios";

function App() {
  const [rangkuman, setRangkuman] = useState();
  const [detail, setDetail] = useState();

  useEffect(() => {
    axios
      .get("http://localhost:3000/api/rangkuman")
      .then((response) => {
        setRangkuman(response.data);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });

    axios
      .get("http://localhost:3000/api/detail")
      .then((response) => {
        setDetail(response.data);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }, []);

  return (
    <div>
      <div className="text-3xl font-bold mb-6">Rangkuman Laba Tahunan</div>
      <table className="border border-black mb-10 mx-auto">
        <thead>
          <tr>
            <th className="p-2 bg-slate-200">Nama</th>
            <th className="p-2 bg-slate-200">Laba Tahunan</th>
          </tr>
        </thead>
        <tbody>
          {rangkuman?.map((data,i)=>(
            <tr key={i}>
              <td className="p-2">{data.Nama}</td>
              <td className="p-2">{data.LabaTahunan}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <div className="text-3xl font-bold mb-6">Detail Laba Tahunan</div>
      <table className="border border-black">
        <thead>
          <tr>
            <th className="p-2 bg-slate-200">Nama Produk</th>
            <th className="p-2 bg-slate-200">Harga Produk</th>
            <th className="p-2 bg-slate-200">Jumlah</th>
            <th className="p-2 bg-slate-200">Subtotal</th>
            <th className="p-2 bg-slate-200">Pemesan</th>
            <th className="p-2 bg-slate-200">Penghasilan Kotor</th>
            <th className="p-2 bg-slate-200">Laba</th>
          </tr>
        </thead>
        <tbody>
          {detail?.map((data,i)=>(
            <tr key={i}>
              <td className="p-2">{data.NamaProduk}</td>
              <td className="p-2">{data.HargaProduk}</td>
              <td className="p-2">{data.Jumlah}</td>
              <td className="p-2">{data.Subtotal}</td>
              <td className="p-2">{data.Pemesan}</td>
              <td className="p-2">{data.PenghasilanKotor}</td>
              <td className="p-2">{data.Laba}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default App;
