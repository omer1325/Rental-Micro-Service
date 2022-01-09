package com.appsdeveloperblog.rentacar.api.rental.business.abstracts;

import com.appsdeveloperblog.rentacar.api.rental.business.dtos.CarSearchListDto;
import com.appsdeveloperblog.rentacar.api.rental.business.request.carRequests.CreateCarRequest;
import com.appsdeveloperblog.rentacar.api.rental.business.request.carRequests.DeleteCarRequest;
import com.appsdeveloperblog.rentacar.api.rental.business.request.carRequests.UpdateCarRequest;
import com.appsdeveloperblog.rentacar.api.rental.core.utilities.results.Result;

import java.util.List;

public interface CarService {
    List<CarSearchListDto> getCars();
    Result save(CreateCarRequest createCarRequest);
    Result delete(DeleteCarRequest deleteCarRequest);
    Result update(UpdateCarRequest updateCarRequest);

}
