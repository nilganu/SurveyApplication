package com.cahrusat.surveyapplication.database

import androidx.room.Embedded
import androidx.room.Relation

data class HeadWithMembers (
    @Embedded val family_head:HeadOftheFamilyEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val family_members:List<FamilyMemberEntity>


)