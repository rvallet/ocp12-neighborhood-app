package com.neighborhood.msneighborhood.ws.exception;

import feign.Feign;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();
    private final String ERREUR400_MSG = "Requête incorrecte";

    /**
     * Implement this method in order to decode an HTTP {@link Response} when {@link
     * Response#status()} is not in the 2xx range. Please raise  application-specific exceptions where
     * possible. If your exception is retryable, wrap or subclass {@link RetryableException}
     *
     * @param methodKey {@link Feign#configKey} of the java method that invoked the request.
     *                  ex. {@code IAM#getUser()}
     * @param response  HTTP response where {@link Response#status() status} is greater than or equal
     *                  to {@code 300}.
     * @return Exception IOException, if there was a network error reading the response or an
     * application-specific exception decoded by the implementation. If the throwable is retryable, it
     * should be wrapped, or a subtype of {@link RetryableException}
     */
    @Override
    public Exception decode(String methodKey, Response response) {

        if(response.status() == 400 ) {
            return new NoSuchResultException(ERREUR400_MSG);
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }

}
