package com.Elite.Erum_Makeover.Repository;

import com.Elite.Erum_Makeover.Model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends MongoRepository<Image, String>
{

    // 🔥 Find image by profileId
    Image findByProfileId(String profileId);
}