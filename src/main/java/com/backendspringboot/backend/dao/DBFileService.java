package com.backendspringboot.backend.dao;

import com.backendspringboot.backend.entity.DBFile;
import com.backendspringboot.backend.repository.DBFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class DBFileService {
    @Autowired
    private DBFileRepository dbFileRepository;
    public DBFile storeFile(MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try{
            if(fileName.contains("..")){
                throw new Exception("Sorry! Filename contains invalid path sequence"+fileName);
            }
            DBFile dbFile = new DBFile(fileName,file.getContentType(),file.getBytes());
            return dbFileRepository.save(dbFile);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }


    public DBFile getFile(String fileId)  {
        return dbFileRepository.findById(fileId).get();
    }

    public List<DBFile> getAllFiles(){
        return dbFileRepository.findAll();
    }
}

