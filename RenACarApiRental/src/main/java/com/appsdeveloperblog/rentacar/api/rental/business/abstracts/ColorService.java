package com.appsdeveloperblog.rentacar.api.rental.business.abstracts;

import java.util.List;

import com.appsdeveloperblog.rentacar.api.rental.business.dtos.ColorSearchListDto;
import com.appsdeveloperblog.rentacar.api.rental.business.request.colorRequests.CreateColorRequest;
import com.appsdeveloperblog.rentacar.api.rental.business.request.colorRequests.DeleteColorRequest;
import com.appsdeveloperblog.rentacar.api.rental.business.request.colorRequests.UpdateColorRequest;
import com.appsdeveloperblog.rentacar.api.rental.core.utilities.results.Result;

public interface ColorService {
	List<ColorSearchListDto> getColors();
	Result save(CreateColorRequest createColorRequest);
	Result delete(DeleteColorRequest deleteColorRequest);
	Result update(UpdateColorRequest updateColorRequest);
}
