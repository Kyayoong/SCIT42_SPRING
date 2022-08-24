package net.softsociety.spring5.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board{
	int boardnum;		//자동 입력(seq)
	String memberid;	//컨트롤러에서 입력
	String title;		//폼 입력
	String contents;	//폼 입력
	String inputdate;	//default sysdate
	int hits;			//default 0
	String originalfile;//null 가능
	String savedfile;	//null 가능
}
