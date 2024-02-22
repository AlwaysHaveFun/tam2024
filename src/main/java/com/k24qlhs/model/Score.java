package com.k24qlhs.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Score {
    private int scid;
    private float score;
    private int typeId;
    private int sjId;
    private String typeName;
    private String sjName;
    private int sid;
}
