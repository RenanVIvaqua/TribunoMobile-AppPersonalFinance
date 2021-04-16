package com.example.tribunomobile.service.model

import androidx.room.Embedded
import androidx.room.Relation

data class OperationWithDetailModel(

    @Embedded
    val operation: OperationModel,

    @Relation(
        parentColumn = "id",
        entityColumn =  "idOperation"
    )
    val operationDetailModel: OperationDetailModel?
)