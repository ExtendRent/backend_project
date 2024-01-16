//package source_files.controllers;
//
//import jakarta.validation.Valid;
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import source_files.data.DTO.paperWorkDTOs.PaymentDetailsDTO;
//import source_files.data.DTO.paperWorkDTOs.ShowRentalResponse;
//import source_files.data.models.paperWorkEntities.paymentEntities.CreditCardInformation;
//import source_files.data.requests.paperworkRequests.RentalRequests.AddRentalRequest;
//import source_files.data.responses.TResponse;
//import source_files.services.paperWorkServices.abstracts.PaymentService;
//import source_files.services.paperWorkServices.abstracts.RentalService;
//
//@RestController
//@RequestMapping("api/v1/payment")
//@AllArgsConstructor
//public class PaymentController {
//
//    private PaymentService paymentService;
//    private RentalService rentalService;
//
//    //@PutMapping(params = {"amount", "paymentTypeId"})
//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<TResponse<PaymentDetailsDTO>> payWithCreditCard(
//            @Valid @RequestBody AddRentalRequest addRentalRequest) {
//
//        return ResponseEntity.ok(TResponse.<PaymentDetailsDTO>tResponseBuilder()
//                .response(this.paymentService.payWithCreditCard(addRentalRequest))
//                .message("Ödeme işlemi başarılı")
//                .build()
//        );
//    }
//
//
//}
