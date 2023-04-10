package com.mitocode.blog.blogapi.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	private Long id;
	
	@NotEmpty
	@Size(min=2,message="El titulo debe tener 2 caracteres como minimo")
	private String title;

	@NotEmpty
	@Size(min=10,message="El titulo debe tener 10 caracteres como minimo")
	private String description;

	@NotEmpty
	 private String content;
}
