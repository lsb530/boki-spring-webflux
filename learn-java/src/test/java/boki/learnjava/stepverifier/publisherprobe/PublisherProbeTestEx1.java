package boki.learnjava.stepverifier.publisherprobe;


import boki.learnjava.stepverifier.PublisherProbeExample;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.PublisherProbe;

class PublisherProbeTestEx1 {

    @Test
    void publisherProbeTest() {
        PublisherProbe<String> probe = PublisherProbe.of(PublisherProbeExample.useStandbyPower());

        StepVerifier
            .create(PublisherProbeExample.processWith(PublisherProbeExample.useMainPower().log(), probe.mono().log()))
            .expectNextCount(1)
            .verifyComplete();

        probe.assertWasSubscribed();
        probe.assertWasRequested();
        probe.assertWasNotCancelled();
    }
}
