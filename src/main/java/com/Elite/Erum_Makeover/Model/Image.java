package com.Elite.Erum_Makeover.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "images")
@NoArgsConstructor
@AllArgsConstructor
public class Image
{
    @Id
    private String id;
    private String fileName;
    private String imageUrl;

//    public Image(String fileName,String imageUrl)
//    {
//        this.fileName=fileName;
//        this.imageUrl=imageUrl;
//    }
}
