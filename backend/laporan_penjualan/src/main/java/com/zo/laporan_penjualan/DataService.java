package com.zo.laporan_penjualan;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DataService {
    private final JdbcTemplate jdbcTemplate;

    public DataService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getRangkuman() {
        String query = "SELECT tl.tl_nama AS Nama, SUM(sj.sj_total_harga) * tl.tl_laba / 100 * 12 AS \"laba Tahunan\" " +
                "FROM TOKO_LANGGANAN tl JOIN SURAT_JALAN sj ON tl.tl_id = sj.sj_toko_id " +
                "GROUP BY tl.tl_nama";

        return jdbcTemplate.queryForList(query);
    }

    public List<Map<String, Object>> getDetail() {
        String query = "SELECT temp.nama AS \"Nama Produk\", temp.harga AS \"Harga Produk\", " +
                "SUM(k_jumlah) AS \"Jumlah\", SUM(k_subtotal) AS \"Subtotal\", " +
                "temp2.nama AS \"Pemesan\", temp2.tl_laba*SUM(k_subtotal)/100+SUM(k_subtotal) AS \"Penghasilan Kotor\", " +
                "temp2.tl_laba*SUM(k_subtotal)/100 AS \"Laba\" " +
                "FROM (SELECT p.p_id, p.p_nama AS \"nama\", p.p_harga AS \"harga\", k.k_jumlah, k.k_subtotal, k.k_suratjalan_id AS \"suratjalan\" " +
                "FROM PRODUK p JOIN KIRIMAN k ON p.p_id = k.k_produk_id) AS temp " +
                "JOIN (SELECT sj.sj_id, tl.tl_nama AS nama, tl.tl_laba FROM SURAT_JALAN sj " +
                "JOIN TOKO_LANGGANAN tl ON sj.sj_toko_id = tl.tl_id) AS temp2 ON temp.suratjalan = temp2.sj_id " +
                "GROUP BY temp.nama, temp.harga, temp2.nama ORDER BY temp.p_id, temp2.nama";

        return jdbcTemplate.queryForList(query);
    }
}
