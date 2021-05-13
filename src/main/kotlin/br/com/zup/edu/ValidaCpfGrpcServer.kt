package br.com.zup.edu


import com.google.protobuf.Any
import com.google.rpc.Code
import com.google.rpc.StatusProto
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.grpc.stub.StreamObserver
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class ValidaCpfGrpcServer : ValidacpfServiceGrpc.ValidacpfServiceImplBase() {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun valida(request: ValidacpfRequest?, responseObserver: StreamObserver<ValidacpfResponse>?) {
        var cpf: String? = request?.cpf
        logger.info("CPF: $cpf")
        if (request?.cpf != null) {
            if (request.cpf.matches("[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}".toRegex()) || request.cpf.matches("[0-9]{11}".toRegex())) {
                val valido = validaCpf(request.cpf)
                if (valido) {
                    var response = ValidacpfResponse.newBuilder()
                        .setCpf(cpf)
                        .setMensagem("Cpf é válido")
                        .build()
                    responseObserver!!.onNext(response)
                    responseObserver.onCompleted()
                    return;
                }
            } else if (cpf!!.isBlank()) {
                var statusProto = com.google.rpc.Status.newBuilder()
                    .setCode(Code.INVALID_ARGUMENT.number)
                    .setMessage("Valor de cpf não pode ser nulo").addDetails(
                        Any.pack(
                            ErrorDetails
                                .newBuilder()
                                .setCode(400)
                                .setMessage("O CPF NAO DEVE SER EM BRANCO").build()
                        )
                    ).build()

                var e = io.grpc.protobuf.StatusProto.toStatusRuntimeException(statusProto)
                responseObserver!!.onError(e)

            } else {
                var e = Status.INVALID_ARGUMENT
                    .withDescription("valor informado nao corresponde a um cpf")
                    .asRuntimeException()
                responseObserver!!.onError(e)
            }
        }


    }

}

