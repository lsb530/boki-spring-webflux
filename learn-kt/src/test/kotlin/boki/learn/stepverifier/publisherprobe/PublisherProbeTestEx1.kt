package boki.learn.stepverifier.publisherprobe

import boki.learn.stepverifier.PublisherProbeExample
import org.junit.jupiter.api.Test
import reactor.test.StepVerifier
import reactor.test.publisher.PublisherProbe

class PublisherProbeTestEx1 {

    @Test
    fun publisherProbeTest() {
        val probe = PublisherProbe.of(PublisherProbeExample.useStandbyPower())

        StepVerifier
            .create(
                PublisherProbeExample.processWith(
                    PublisherProbeExample.useMainPower().log(), probe.mono().log()
                )
            )
            .expectNextCount(1)
            .verifyComplete()

        probe.assertWasSubscribed()
        probe.assertWasRequested()
        probe.assertWasNotCancelled()
    }
}