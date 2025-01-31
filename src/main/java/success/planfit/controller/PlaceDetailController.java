package success.planfit.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import success.planfit.controller.utils.ControllerUtil;
import success.planfit.dto.request.PlaceDetailRequestDto;
import success.planfit.dto.request.PlaceRelevanceDetail;
import success.planfit.dto.response.LocationDetailResponseDto;
import success.planfit.dto.response.PlaceDetailResponseDto;
import success.planfit.service.PlaceDetailService;

import java.security.Principal;

@AllArgsConstructor
@RestController
public class PlaceDetailController {

    private final PlaceDetailService placeDetailService;
    private final ControllerUtil controllerUtil;

    // AI에게 placeId로 요청받는 경우
    @GetMapping("/place/detailList/")
    public ResponseEntity<PlaceDetailResponseDto> placeDetail(
            @RequestBody PlaceRelevanceDetail placeRelevanceDetail
    ){
        return ResponseEntity.ok(placeDetailService.getPlaceDetailsById(placeRelevanceDetail));
    }

    // 프론트에게 위도 경도 radius로 요청받는 경우
    @GetMapping("/place/nearByDetails")
    public ResponseEntity<LocationDetailResponseDto> placeDetails(
            Principal principal
            ,@RequestBody PlaceDetailRequestDto requestDto
    ){
        Long id = controllerUtil.findUserIdByPrincipal(principal);
        return ResponseEntity.ok(placeDetailService.passPlaceDetail(id, requestDto));
    }


}
