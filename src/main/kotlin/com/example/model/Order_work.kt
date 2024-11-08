package com.example.model

data class order_work_id(
    val id_order: String,
    val id_work: String,
    val id_equipment: String,
    val id_employee: String,
    val id_assortment: String,
)
data class order_work(
    val count: String,
    val status: String,
    val price: String,
    val id_price: String,
)