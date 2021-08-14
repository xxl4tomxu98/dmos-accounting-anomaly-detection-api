package com.backend.template.dmosbackendtemplate.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sagemakerruntime.SageMakerRuntimeClient;
import software.amazon.awssdk.services.sagemakerruntime.model.InvokeEndpointRequest;
import software.amazon.awssdk.services.sagemakerruntime.model.InvokeEndpointResponse;

@Service
public class SentimentService
{
    public String getSentiment()
    {
        // Set Variables
        String endpointName = "pytorch-inference-2021-08-10-19-25-44-438";
        String contentType = "text/csv";

        // Read payload into a variable
        SdkBytes body = SdkBytes.fromUtf8String("The simplest pleasures in life are the best, and this film is one of them. Combining a rather basic storyline of love and adventure this movie transcends the usual weekend fair with wit and unmitigated charm.");

        // Build an Invocation request object
        InvokeEndpointRequest request = InvokeEndpointRequest.builder().contentType(contentType).body(body)
                .endpointName(endpointName).build();

        // Build AmazonSageMakerRuntime client
        // Uses the default credential provider chain which "should" pull the token from the pod. This also allow
        // the use of the credential file. See https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/credentials.html
        SageMakerRuntimeClient runtime = SageMakerRuntimeClient.builder().region(Region.US_EAST_1).build();

        // Perform an inference
        InvokeEndpointResponse result = runtime.invokeEndpoint(request);

        // Print inference result
        return result.body().asUtf8String();
    }
}
