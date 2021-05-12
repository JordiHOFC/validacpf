package br.com.zup.edu

import io.grpc.stub.StreamObserver
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class ValidaCpfGrpcServer : ValidacpfServiceGrpc.ValidacpfServiceImplBase() {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun valida(request: ValidacpfRequest?, responseObserver: StreamObserver<ValidacpfResponse>?) {
        var cpf:String? = request?.cpf
        logger.info("CPF: $cpf")
        if (request?.cpf != null) {
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
        }

        cpf = "INVALID OR NULL"
        var response = ValidacpfResponse.newBuilder()
            .setCpf(cpf)
            .setMensagem("Invalid")
            .build()
        responseObserver!!.onNext(response)
        responseObserver.onCompleted()

    }

}

