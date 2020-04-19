package com.zeitgeist.employee.Services;

import com.zeitgeist.employee.Models.Consumption;
import com.zeitgeist.employee.Models.Customer;
import com.zeitgeist.employee.Models.FabricLiningItem;
import com.zeitgeist.employee.Models.GridListItem;
import com.zeitgeist.employee.Models.LiningItem;
import com.zeitgeist.employee.Models.Measurements;
import com.zeitgeist.employee.Models.OrderDetail;
import com.zeitgeist.employee.Models.OrderListData;
import com.zeitgeist.employee.Models.Stylings;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by User on 8/18/2017.
 */

public interface APIService {

    @FormUrlEncoded
    @POST("loginprocessing.php")
    Call<String> ProcessLogin(@Field("s_id") String shopId,
                               @Field("s_password") String shopPw);

    @FormUrlEncoded
    @POST("listitemprocessing.php")
    Call<List<GridListItem>> ProcessListItem(@Field("listId") String listId);

    @FormUrlEncoded
    @POST("newcustomerdetailsprocessing.php")
    Call<String> ProcessNewCustomerDetails(@Field("nameValue") String nameValue,
                                           @Field("contactPrimaryValue") String contactPrimaryValue,
                                           @Field("contactSecondaryValue") String contactSecondaryValue,
                                           @Field("emailValue") String emailValue,
                                           @Field("cityValue") String cityValue,
                                           @Field("addressValue") String addressValue);


    @FormUrlEncoded
    @POST("imageuploadprocessing.php")
    Call<String> ProcessImageUpload(@Field("customerId") String customerId,
                              @Field("orderId") String orderId,
                              @Field("imgName") String imgName,
                              @Field("imgNum") String imgNum,
                              @Field("file") String file);


    @FormUrlEncoded
    @POST("fabricprocessing.php")
    Call<List<FabricLiningItem>> ProcessFabricItem(@Field("garment") String garment);


    @FormUrlEncoded
    @POST("liningprocessing.php")
    Call<List<LiningItem>> ProcessLiningItem(@Field("garment") String garment);

    @FormUrlEncoded
    @POST("measurementinsertion.php")
    Call<String> InsertMeasurement(@Field("orderId") String orderId,
                                   @Field("customerId") String customerId,
                                   @Field("stanceId") String stanceId,
                                   @Field("stance") String stance,
                                   @Field("stanceImg") String stanceImg,
                                   @Field("shoulderSlopeId") String shoulderSlopeId,
                                   @Field("shoulderSlope") String shoulderSlope,
                                   @Field("shoulderSlopeImg") String shoulderSlopeImg,
                                   @Field("chestDescriptionId") String chestDescriptionId,
                                   @Field("chestDescription") String chestDescription,
                                   @Field("chestDescriptionImg") String chestDescriptionImg,
                                   @Field("stomachDescriptionId") String stomachDescriptionId,
                                   @Field("stomachDescription") String stomachDescription,
                                   @Field("stomachDescriptionImg") String stomachDescriptionImg,
                                   @Field("hipDescriptionId") String hipDescriptionId,
                                   @Field("hipDescription") String hipDescription,
                                   @Field("hipDescriptionImg") String hipDescriptionImg,
                                   @Field("neck") String neck,
                                   @Field("fullChest") String fullChest,
                                   @Field("fullShoulderWidth") String fullShoulderWidth,
                                   @Field("rightSleeve") String rightSleeve,
                                   @Field("leftSleeve") String leftSleeve,
                                   @Field("bicep") String bicep,
                                   @Field("wrist") String wrist,
                                   @Field("waistStomach") String waistStomach,
                                   @Field("hip") String hip,
                                   @Field("frontJacketLength") String frontJacketLength,
                                   @Field("frontChestWidth") String frontChestWidth,
                                   @Field("backWidth") String backWidth,
                                   @Field("halfShoulderWidthLeft") String halfShoulderWidthLeft,
                                   @Field("halfShoulderWidthRight") String halfShoulderWidthRight,
                                   @Field("fullBackLength") String fullBackLength,
                                   @Field("halfBackLength") String halfBackLength,
                                   @Field("trouserWaist") String trouserWaist,
                                   @Field("trouserOutseam") String trouserOutseam,
                                   @Field("trouserInseam") String trouserInseam,
                                   @Field("crotch") String crotch,
                                   @Field("thigh") String thigh,
                                   @Field("knee") String knee,
                                   @Field("rightFullSleeve") String rightFullSleeve,
                                   @Field("leftFullSleeve") String leftFullSleeve);


    @FormUrlEncoded
    @POST("stylinginsertion.php")
    Call<String> InsertStyling(@Field("orderId") String orderId,
                               @Field("customerId") String customerId,
                               @Field("fabricIdValue") String fabricIdValue,
                               @Field("fabricNumberValue") String fabricNumberValue,
                               @Field("liningIdValue") String liningIdValue,
                               @Field("liningNumberValue") String liningNumberValue,
                               @Field("trouserFitId") String trouserFitId,
                               @Field("trouserFitValue") String trouserFitValue,
                               @Field("trouserFitImgValue") String trouserFitImgValue,
                               @Field("jacketStyleId") String jacketStyleId,
                               @Field("jacketStyleValue") String jacketStyleValue,
                               @Field("jacketStyleImgValue") String jacketStyleImgValue,
                               @Field("frontPanelRoundnessId") String frontPanelRoundnessId,
                               @Field("frontPanelRoundnessValue") String frontPanelRoundnessValue,
                               @Field("frontPanelRoundnessImgValue") String frontPanelRoundnessImgValue,
                               @Field("jacketLengthId") String jacketLengthId,
                               @Field("jacketLengthValue") String jacketLengthValue,
                               @Field("jacketLengthImgValue") String jacketLengthImgValue,
                               @Field("lapelStyleId") String lapelStyleId,
                               @Field("lapelStyleValue") String lapelStyleValue,
                               @Field("lapelStyleImgValue") String lapelStyleImgValue,
                               @Field("lapelCurvatureId") String lapelCurvatureId,
                               @Field("lapelCurvatureValue") String lapelCurvatureValue,
                               @Field("lapelCurvatureImgValue") String lapelCurvatureImgValue,
                               @Field("lapelPickStitchId") String lapelPickStitchId,
                               @Field("lapelPickStitchValue") String lapelPickStitchValue,
                               @Field("lapelPickStitchImgValue") String lapelPickStitchImgValue,
                               @Field("shoulderConstructionId") String shoulderConstructionId,
                               @Field("shoulderConstructionValue") String shoulderConstructionValue,
                               @Field("shoulderConstructionImgValue") String shoulderConstructionImgValue,
                               @Field("ventStyleId") String ventStyleId,
                               @Field("ventStyleValue") String ventStyleValue,
                               @Field("ventStyleImgValue") String ventStyleImgValue,
                               @Field("breastPocketId") String breastPocketId,
                               @Field("breastPocketValue") String breastPocketValue,
                               @Field("breastPocketImgValue") String breastPocketImgValue,
                               @Field("sidePocketId") String sidePocketId,
                               @Field("sidePocketValue") String sidePocketValue,
                               @Field("sidePocketImgValue") String sidePocketImgValue,
                               @Field("ticketPocketId") String ticketPocketId,
                               @Field("ticketPocketValue") String ticketPocketValue,
                               @Field("ticketPocketImgValue") String ticketPocketImgValue,
                               @Field("cuffButtonStyleId") String cuffButtonStyleId,
                               @Field("cuffButtonStyleValue") String cuffButtonStyleValue,
                               @Field("cuffButtonStyleImgValue") String cuffButtonStyleImgValue,
                               @Field("functionalCuffsId") String functionalCuffsId,
                               @Field("functionalCuffsValue") String functionalCuffsValue,
                               @Field("functionalCuffsImgValue") String functionalCuffsImgValue,
                               @Field("trouserPleatsId") String trouserPleatsId,
                               @Field("trouserPleatsValue") String trouserPleatsValue,
                               @Field("trouserPleatsImgValue") String trouserPleatsImgValue,
                               @Field("trouserBackPocketId") String trouserBackPocketId,
                               @Field("trouserBackPocketValue") String trouserBackPocketValue,
                               @Field("trouserBackPocketImgValue") String trouserBackPocketImgValue,
                               @Field("trouserCuffId") String trouserCuffId,
                               @Field("trouserCuffValue") String trouserCuffValue,
                               @Field("trouserCuffImgValue") String trouserCuffImgValue,
                               @Field("trouserLoopTabId") String trouserLoopTabId,
                               @Field("trouserLoopTabValue") String trouserLoopTabValue,
                               @Field("trouserLoopTabImgValue") String trouserLoopTabImgValue,
                               @Field("waistCoatId") String waistCoatId,
                               @Field("waistCoatValue") String waistCoatValue,
                               @Field("waistCoatImgValue") String waistCoatImgValue,
                               @Field("waistCoatPocketTypeId") String waistCoatPocketTypeId,
                               @Field("waistCoatPocketTypeValue") String waistCoatPocketTypeValue,
                               @Field("waistCoatPocketTypeImgValue") String waistCoatPocketTypeImgValue,
                               @Field("waistCoatBackId") String waistCoatBackId,
                               @Field("waistCoatBackValue") String waistCoatBackValue,
                               @Field("waistCoatBackImgValue") String waistCoatBackImgValue,
                               @Field("buttonsId") String buttonsId,
                               @Field("buttonsValue") String buttonsValue,
                               @Field("buttonsImgValue") String buttonsImgValue,
                               @Field("lapelEyeletColorId") String lapelEyeletColorId,
                               @Field("lapelEyeletColorValue") String lapelEyeletColorValue,
                               @Field("lapelEyeletColorImgValue") String lapelEyeletColorImgValue,
                               @Field("cuffEyeletColorId") String cuffEyeletColorId,
                               @Field("cuffEyeletColorValue") String cuffEyeletColorValue,
                               @Field("cuffEyeletColorImgValue") String cuffEyeletColorImgValue,
                               @Field("pipingColorId") String pipingColorId,
                               @Field("pipingColorValue") String pipingColorValue,
                               @Field("pipingColorImgValue") String pipingColorImgValue,
                               @Field("meltonUndercollarNumberId") String meltonUndercollarNumberId,
                               @Field("meltonUndercollarNumberValue") String meltonUndercollarNumberValue,
                               @Field("meltonUndercollarNumberImgValue") String meltonUndercollarNumberImgValue,
                               @Field("jacketFitId") String jacketFitId,
                               @Field("jacketFitValue") String jacketFitValue,
                               @Field("jacketFitImgValue") String jacketFitImgValue,
                               @Field("lapelSizeId") String lapelSizeId,
                               @Field("lapelSizeValue") String lapelSizeValue,
                               @Field("lapelSizeImgValue") String lapelSizeImgValue);

    @FormUrlEncoded
    @POST("customersearchingbyId.php")
    Call<Customer> SearchCustomerById(@Field("customerId") String customerId);

    @FormUrlEncoded
    @POST("customersearchingbyemail.php")
    Call<Customer> SearchCustomerByEmail(@Field("customerEmail") String customerEmail);

    @FormUrlEncoded
    @POST("customersearchingbyphone.php")
    Call<Customer> SearchCustomerByPhone(@Field("customerPhone") String customerPhone);

    @FormUrlEncoded
    @POST("ordersprocessing.php")
    Call<List<OrderDetail>> ProcessOrders(@Field("customerId") String customerId);

    @GET("cityprocessing.php")
    Call<ArrayList<String>> ProcessCities();

    @FormUrlEncoded
    @POST("consumptionretrival.php")
    Call<Consumption> RetriveConsumption(@Field("garmentId") String garmentId);

    @FormUrlEncoded
    @POST("basepatternprocessing.php")
    Call<ArrayList<String>> ProcessBasePattern(@Field("garmentId") String garmentId);

    @FormUrlEncoded
    @POST("basesizeprocessing.php")
    Call<ArrayList<String>> ProcessBaseSize(@Field("garmentId") String garmentId);

    @FormUrlEncoded
    @POST("fabricconsumptionprocessing.php")
    Call<String> ProcessFabricConsumption(@Field("fabricConsumption") String fabricConsumption,
                                     @Field("fabricId") String fabricId);

    @FormUrlEncoded
    @POST("liningconsumptionprocessing.php")
    Call<String> ProcessLiningConsumption(@Field("liningConsumption") String liningConsumption,
                                          @Field("liningId") String liningId);

    @FormUrlEncoded
    @POST("phonenumberprocessing.php")
    Call<String> ProcessPhoneNumber(@Field("phoneNumber") String phoneNumber);

    @FormUrlEncoded
    @POST("emailprocessing.php")
    Call<String> ProcessEmail(@Field("email") String email);

    @FormUrlEncoded
    @POST("orderdetailprocessing.php")
    Call<OrderDetail> ProcessOrderDetail(@Field("orderId") String orderId);

    @FormUrlEncoded
    @POST("measurementprocessing.php")
    Call<Measurements> ProcessMeasurement(@Field("customerId") String customerId,
                                          @Field("orderId") String orderId);

    @FormUrlEncoded
    @POST("stylingprocessing.php")
    Call<Stylings> ProcessStyling(@Field("customerId") String customerId,
                                  @Field("orderId") String orderId);


    @FormUrlEncoded
    @POST("existingcustomerprocessing.php")
    Call<String> ProcessExistingCustomerDetails(@Field("customerId") String customerId,
                                           @Field("nameValue") String nameValue,
                                           @Field("contactPrimaryValue") String contactPrimaryValue,
                                           @Field("contactSecondaryValue") String contactSecondaryValue,
                                           @Field("emailValue") String emailValue,
                                           @Field("cityValue") String cityValue,
                                           @Field("addressValue") String addressValue);

    @FormUrlEncoded
    @POST("orderinsertion.php")
    Call<String> InsertOrder(@Field("orderId") String orderId,
                             @Field("customerId") String customerId,
                             @Field("shopId") String shopId,
                             @Field("itemType") String itemType,
                             @Field("status") String status,
                             @Field("price") String price,
                             @Field("deliveryDate") String deliveryDate,
                             @Field("orderType") String orderType,
                             @Field("basePattern") String basePattern,
                             @Field("baseSize") String baseSize,
                             @Field("fabricId") String fabricId,
                             @Field("liningId") String liningId);

    @FormUrlEncoded
    @POST("measurementupdation.php")
    Call<String> UpdateMeasurement(@Field("measurementId") String measurementId,
                                   @Field("stanceId") String stanceId,
                                   @Field("stance") String stance,
                                   @Field("stanceImg") String stanceImg,
                                   @Field("shoulderSlopeId") String shoulderSlopeId,
                                   @Field("shoulderSlope") String shoulderSlope,
                                   @Field("shoulderSlopeImg") String shoulderSlopeImg,
                                   @Field("chestDescriptionId") String chestDescriptionId,
                                   @Field("chestDescription") String chestDescription,
                                   @Field("chestDescriptionImg") String chestDescriptionImg,
                                   @Field("stomachDescriptionId") String stomachDescriptionId,
                                   @Field("stomachDescription") String stomachDescription,
                                   @Field("stomachDescriptionImg") String stomachDescriptionImg,
                                   @Field("hipDescriptionId") String hipDescriptionId,
                                   @Field("hipDescription") String hipDescription,
                                   @Field("hipDescriptionImg") String hipDescriptionImg,
                                   @Field("neck") String neck,
                                   @Field("fullChest") String fullChest,
                                   @Field("fullShoulderWidth") String fullShoulderWidth,
                                   @Field("rightSleeve") String rightSleeve,
                                   @Field("leftSleeve") String leftSleeve,
                                   @Field("bicep") String bicep,
                                   @Field("wrist") String wrist,
                                   @Field("waistStomach") String waistStomach,
                                   @Field("hip") String hip,
                                   @Field("frontJacketLength") String frontJacketLength,
                                   @Field("frontChestWidth") String frontChestWidth,
                                   @Field("backWidth") String backWidth,
                                   @Field("halfShoulderWidthLeft") String halfShoulderWidthLeft,
                                   @Field("halfShoulderWidthRight") String halfShoulderWidthRight,
                                   @Field("fullBackLength") String fullBackLength,
                                   @Field("halfBackLength") String halfBackLength,
                                   @Field("trouserWaist") String trouserWaist,
                                   @Field("trouserOutseam") String trouserOutseam,
                                   @Field("trouserInseam") String trouserInseam,
                                   @Field("crotch") String crotch,
                                   @Field("thigh") String thigh,
                                   @Field("knee") String knee,
                                   @Field("rightFullSleeve") String rightFullSleeve,
                                   @Field("leftFullSleeve") String leftFullSleeve);

    @FormUrlEncoded
    @POST("stylingupdation.php")
    Call<String> UpdateStyling(@Field("stylingId") String stylingId,
                               @Field("fabricIdValue") String fabricIdValue,
                               @Field("fabricNumberValue") String fabricNumberValue,
                               @Field("liningIdValue") String liningIdValue,
                               @Field("liningNumberValue") String liningNumberValue,
                               @Field("trouserFitId") String trouserFitId,
                               @Field("trouserFitValue") String trouserFitValue,
                               @Field("trouserFitImgValue") String trouserFitImgValue,
                               @Field("jacketStyleId") String jacketStyleId,
                               @Field("jacketStyleValue") String jacketStyleValue,
                               @Field("jacketStyleImgValue") String jacketStyleImgValue,
                               @Field("frontPanelRoundnessId") String frontPanelRoundnessId,
                               @Field("frontPanelRoundnessValue") String frontPanelRoundnessValue,
                               @Field("frontPanelRoundnessImgValue") String frontPanelRoundnessImgValue,
                               @Field("jacketLengthId") String jacketLengthId,
                               @Field("jacketLengthValue") String jacketLengthValue,
                               @Field("jacketLengthImgValue") String jacketLengthImgValue,
                               @Field("lapelStyleId") String lapelStyleId,
                               @Field("lapelStyleValue") String lapelStyleValue,
                               @Field("lapelStyleImgValue") String lapelStyleImgValue,
                               @Field("lapelCurvatureId") String lapelCurvatureId,
                               @Field("lapelCurvatureValue") String lapelCurvatureValue,
                               @Field("lapelCurvatureImgValue") String lapelCurvatureImgValue,
                               @Field("lapelPickStitchId") String lapelPickStitchId,
                               @Field("lapelPickStitchValue") String lapelPickStitchValue,
                               @Field("lapelPickStitchImgValue") String lapelPickStitchImgValue,
                               @Field("shoulderConstructionId") String shoulderConstructionId,
                               @Field("shoulderConstructionValue") String shoulderConstructionValue,
                               @Field("shoulderConstructionImgValue") String shoulderConstructionImgValue,
                               @Field("ventStyleId") String ventStyleId,
                               @Field("ventStyleValue") String ventStyleValue,
                               @Field("ventStyleImgValue") String ventStyleImgValue,
                               @Field("breastPocketId") String breastPocketId,
                               @Field("breastPocketValue") String breastPocketValue,
                               @Field("breastPocketImgValue") String breastPocketImgValue,
                               @Field("sidePocketId") String sidePocketId,
                               @Field("sidePocketValue") String sidePocketValue,
                               @Field("sidePocketImgValue") String sidePocketImgValue,
                               @Field("ticketPocketId") String ticketPocketId,
                               @Field("ticketPocketValue") String ticketPocketValue,
                               @Field("ticketPocketImgValue") String ticketPocketImgValue,
                               @Field("cuffButtonStyleId") String cuffButtonStyleId,
                               @Field("cuffButtonStyleValue") String cuffButtonStyleValue,
                               @Field("cuffButtonStyleImgValue") String cuffButtonStyleImgValue,
                               @Field("functionalCuffsId") String functionalCuffsId,
                               @Field("functionalCuffsValue") String functionalCuffsValue,
                               @Field("functionalCuffsImgValue") String functionalCuffsImgValue,
                               @Field("trouserPleatsId") String trouserPleatsId,
                               @Field("trouserPleatsValue") String trouserPleatsValue,
                               @Field("trouserPleatsImgValue") String trouserPleatsImgValue,
                               @Field("trouserBackPocketId") String trouserBackPocketId,
                               @Field("trouserBackPocketValue") String trouserBackPocketValue,
                               @Field("trouserBackPocketImgValue") String trouserBackPocketImgValue,
                               @Field("trouserCuffId") String trouserCuffId,
                               @Field("trouserCuffValue") String trouserCuffValue,
                               @Field("trouserCuffImgValue") String trouserCuffImgValue,
                               @Field("trouserLoopTabId") String trouserLoopTabId,
                               @Field("trouserLoopTabValue") String trouserLoopTabValue,
                               @Field("trouserLoopTabImgValue") String trouserLoopTabImgValue,
                               @Field("waistCoatId") String waistCoatId,
                               @Field("waistCoatValue") String waistCoatValue,
                               @Field("waistCoatImgValue") String waistCoatImgValue,
                               @Field("waistCoatPocketTypeId") String waistCoatPocketTypeId,
                               @Field("waistCoatPocketTypeValue") String waistCoatPocketTypeValue,
                               @Field("waistCoatPocketTypeImgValue") String waistCoatPocketTypeImgValue,
                               @Field("waistCoatBackId") String waistCoatBackId,
                               @Field("waistCoatBackValue") String waistCoatBackValue,
                               @Field("waistCoatBackImgValue") String waistCoatBackImgValue,
                               @Field("buttonsId") String buttonsId,
                               @Field("buttonsValue") String buttonsValue,
                               @Field("buttonsImgValue") String buttonsImgValue,
                               @Field("lapelEyeletColorId") String lapelEyeletColorId,
                               @Field("lapelEyeletColorValue") String lapelEyeletColorValue,
                               @Field("lapelEyeletColorImgValue") String lapelEyeletColorImgValue,
                               @Field("cuffEyeletColorId") String cuffEyeletColorId,
                               @Field("cuffEyeletColorValue") String cuffEyeletColorValue,
                               @Field("cuffEyeletColorImgValue") String cuffEyeletColorImgValue,
                               @Field("pipingColorId") String pipingColorId,
                               @Field("pipingColorValue") String pipingColorValue,
                               @Field("pipingColorImgValue") String pipingColorImgValue,
                               @Field("meltonUndercollarNumberId") String meltonUndercollarNumberId,
                               @Field("meltonUndercollarNumberValue") String meltonUndercollarNumberValue,
                               @Field("meltonUndercollarNumberImgValue") String meltonUndercollarNumberImgValue,
                               @Field("jacketFitId") String jacketFitId,
                               @Field("jacketFitValue") String jacketFitValue,
                               @Field("jacketFitImgValue") String jacketFitImgValue,
                               @Field("lapelSizeId") String lapelSizeId,
                               @Field("lapelSizeValue") String lapelSizeValue,
                               @Field("lapelSizeImgValue") String lapelSizeImgValue);

    @FormUrlEncoded
    @POST("orderupdation.php")
    Call<String> UpdateOrder(@Field("orderId") String orderId,
                             @Field("shopId") String shopId,
                             @Field("itemType") String itemType,
                             @Field("status") String status,
                             @Field("price") String price,
                             @Field("deliveryDate") String deliveryDate,
                             @Field("orderType") String orderType,
                             @Field("basePattern") String basePattern,
                             @Field("baseSize") String baseSize,
                             @Field("fabricId") String fabricId,
                             @Field("liningId") String liningId);

    @FormUrlEncoded
    @POST("fabricadditionprocessing.php")
    Call<String> ProcessFabricAddition(@Field("fabricConsumption") String fabricConsumption,
                                          @Field("fabricId") String fabricId);

    @FormUrlEncoded
    @POST("liningadditionprocessing.php")
    Call<String> ProcessLiningAddition(@Field("liningConsumption") String liningConsumption,
                                          @Field("liningId") String liningId);

    @FormUrlEncoded
    @POST("orderlistprocessing.php")
    Call<List<OrderListData>> ProcessOrderList(@Field("shopId") String shopId);

    @FormUrlEncoded
    @POST("orderlistbystatusprocessing.php")
    Call<List<OrderListData>> ProcessOrderListByStatus(@Field("shopId") String shopId,
                                                       @Field("status") String status);

    @FormUrlEncoded
    @POST("ordersearchprocessing.php")
    Call<List<OrderListData>> ProcessOrderSearch(@Field("customerId") String customerId,
                                                 @Field("customerName") String customerName,
                                                 @Field("customerPhone") String customerPhone,
                                                 @Field("customerEmail") String customerEmail,
                                                 @Field("orderId") String orderId,
                                                 @Field("itemType") String itemType,
                                                 @Field("orderType") String orderType,
                                                 @Field("orderDate") String orderDate,
                                                 @Field("deliveryDate") String deliveryDate,
                                                 @Field("shopId") String shopId);

    @FormUrlEncoded
    @POST("sellbtnprocessing.php")
    Call<String> ProcessSellBtn(@Field("orderId") String orderId);

    @FormUrlEncoded
    @POST("fabricretrival.php")
    Call<FabricLiningItem> RetriveFabric(@Field("fabricId") String fabricId);

    @FormUrlEncoded
    @POST("liningretrival.php")
    Call<LiningItem> RetriveLining(@Field("liningId") String liningId);

    @FormUrlEncoded
    @POST("feedbackprocessing.php")
    Call<String> ProcessFeedback(@Field("customerId") String customerId,
                                 @Field("orderId") String orderId,
                                 @Field("customerName") String customerName,
                                 @Field("answer1") String answer1,
                                 @Field("answer2") String answer2,
                                 @Field("answer3") String answer3,
                                 @Field("answer4") String answer4,
                                 @Field("answer5") String answer5,
                                 @Field("answer6") String answer6,
                                 @Field("answer7") String answer7,
                                 @Field("answer8") String answer8);

    @FormUrlEncoded
    @POST("feedbackscoreprocessing.php")
    Call<String> ProcessFeedbackScore(@Field("shopId") String shopId,
                                      @Field("score1") String score1,
                                      @Field("score2") String score2,
                                      @Field("score3") String score3,
                                      @Field("score4") String score4,
                                      @Field("score5") String score5,
                                      @Field("score6") String score6,
                                      @Field("score7") String score7,
                                      @Field("score8") String score8);

    @FormUrlEncoded
    @POST("statustimelineprocessing.php")
    Call<String> ProcessStatusTimeline(@Field("orderId") String orderId,
                             @Field("orderStatus") String orderStatus);

    @FormUrlEncoded
    @POST("statustimelineupdation.php")
    Call<String> UpdateStatusTimeline(@Field("orderId") String orderId,
                                       @Field("orderStatus") String orderStatus);

    @FormUrlEncoded
    @POST("measurementimageprocessing.php")
    Call<String> ProcessMeasurementImage(@Field("measurementId") String measurementId);

    @FormUrlEncoded
    @POST("customfabricinsertion.php")
    Call<String> InsertCustomerFabric(@Field("orderId") String orderId);

    @FormUrlEncoded
    @POST("retrivecustomfabricnumber.php")
    Call<String> RetriveCustomFabricNumber(@Field("orderId") String orderId);
}
