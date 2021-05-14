package br.com.zup.edu

import com.google.api.Http
import io.micronaut.grpc.server.GrpcEmbeddedServer
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.ok
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import javax.inject.Inject

@Controller
class GrpcServerController(@Inject val grpcServer: GrpcEmbeddedServer) {
    @Get("/stop")
    fun stop(): HttpResponse<Any>{
            grpcServer.stop()
        return ok()
    }
    @Get("/start")
    fun start():HttpResponse<Any>{
        grpcServer.start()
        return ok()
    }
}