package wieczorek.jakub.shop.business.spring.beans.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Converter
{
    private ModelMapper modelMapper;

    @Autowired
    public Converter(ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
    }

    public <S, D> D convert(S baseObject, Class<D> destClazz)
    {
        // TODO handle null as an Optional
        return modelMapper.map(baseObject, destClazz);
    }
}
