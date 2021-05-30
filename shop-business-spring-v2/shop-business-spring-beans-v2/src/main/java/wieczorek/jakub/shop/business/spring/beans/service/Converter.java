package wieczorek.jakub.shop.business.spring.beans.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        return Optional.ofNullable(baseObject).map(object -> modelMapper.map(object, destClazz)).orElse(null);
    }
}
