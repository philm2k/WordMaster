package com.philm2k.wordmaster.model.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class WordWithUsages {
    @Embedded
    Word word;

    @Relation(parentColumn = "id", entityColumn = "word_id", entity = Usage.class)
    List<Usage> usages;
}
