package com.simpletech.ewaysample.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class Transaction(elements: Collection<TransactionElement>) : ArrayList<TransactionElement>(elements)
data class TransactionElement (

    @SerializedName("Id")
    val id: Long,

    @SerializedName("AccountId")
    val accountID: String,

    @SerializedName("TransponderId")
    val transponderID: String,

    @SerializedName("TransactionDate")
    val transactionDate: String,

    @SerializedName("Concession")
    var concession: String,

    @SerializedName("TollStation")
    val tollStation: String,

    @SerializedName("Lane")
    val lane: String,

    @SerializedName("TollCategory")
    val tollCategory: String,

    @SerializedName("AmountWithoutDiscount")
    val amountWithoutDiscount: Double,

    @SerializedName("DiscountPercent")
    val discountPercent: Long,

    @SerializedName("Discount")
    val discount: Long,

    @SerializedName("AmountAfterDiscount")
    val amountAfterDiscount: Double,

    @SerializedName("TaxAmount")
    val taxAmount: Double,

    @SerializedName("TotalAmount")
    val totalAmount: Double
)

enum class Concession(val value: String) {
    ATTIKI("ΑΤΤΙΚΗ ΟΔΟΣ"),
    AEGEAN("ΑΥΤ/ΜΟΣ ΑΙΓΑΙΟΥ"),
    EGNATIA("ΕΓΝΑΤΙΑ ΟΔΟΣ"),
    KENTRIKI("ΚΕΝΤΡΙΚΗ ΟΔΟΣ"),
    NEA("ΝΕΑ ΟΔΟΣ");


}
