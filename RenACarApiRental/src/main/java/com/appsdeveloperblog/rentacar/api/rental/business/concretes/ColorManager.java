package com.appsdeveloperblog.rentacar.api.rental.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.appsdeveloperblog.rentacar.api.rental.business.abstracts.ColorService;
import com.appsdeveloperblog.rentacar.api.rental.business.constants.messages.ColorMessages;
import com.appsdeveloperblog.rentacar.api.rental.business.dtos.ColorSearchListDto;
import com.appsdeveloperblog.rentacar.api.rental.business.request.colorRequests.CreateColorRequest;
import com.appsdeveloperblog.rentacar.api.rental.business.request.colorRequests.DeleteColorRequest;
import com.appsdeveloperblog.rentacar.api.rental.business.request.colorRequests.UpdateColorRequest;
import com.appsdeveloperblog.rentacar.api.rental.core.utilities.mapping.ModelMapperService;
import com.appsdeveloperblog.rentacar.api.rental.core.utilities.results.Result;
import com.appsdeveloperblog.rentacar.api.rental.core.utilities.results.SuccessResult;
import com.appsdeveloperblog.rentacar.api.rental.dataAccess.abstracts.ColorDao;
import com.appsdeveloperblog.rentacar.api.rental.entities.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColorManager implements ColorService {
	
	private ColorDao colorDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {
		super();
		this.colorDao = colorDao;
		this.modelMapperService = modelMapperService;
	}


	@Override
	public List<ColorSearchListDto> getColors() {
		
		List<Color> result = this.colorDao.findAll();
		List<ColorSearchListDto> response = result.stream().map(color->modelMapperService.forDto()
				.map(color, ColorSearchListDto.class)).collect(Collectors.toList());
				
		return response;
	}


	@Override
	public Result save(CreateColorRequest createColorRequest) {
		Color color = modelMapperService.forRequest().map(createColorRequest, Color.class);
		this.colorDao.save(color);
		return new SuccessResult(ColorMessages.add);
	}


	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		Color color = modelMapperService.forRequest().map(deleteColorRequest, Color.class);
		this.colorDao.delete(color);
		return new SuccessResult(ColorMessages.delete);
	}


	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		Color color = modelMapperService.forRequest().map(updateColorRequest, Color.class);
		this.colorDao.save(color);
		return new SuccessResult(ColorMessages.update);
	}
}
