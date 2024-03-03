package com.k24qlhs.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Score {
    private int sid;
    private int scid;
    private int score;
    private int typeId;
    private int sjId;
    private String typeName;
    private String sjName;
    private String sName;
}
