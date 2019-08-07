package com.stackroute.muzixapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//make this class as hibernate entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Track {


  @Id
	private int id;

	private String name;

	private String comment;

}
