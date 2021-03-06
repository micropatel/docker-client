/*
 * Copyright (c) 2015 Spotify AB.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.spotify.docker.client.messages.swarm;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;

@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
public class EndpointSpec {

    public static final String RESOLUTION_MODE_VIP = "vip";
    public static final String RESOLUTION_MODE_DNSRR = "dnsrr";

    @JsonProperty("Mode")
    private String mode;

    @JsonProperty("Ports")
    private ImmutableList<PortConfig> ports;

    public String mode() {
        return mode;
    }

    public List<PortConfig> ports() {
        return ports;
    }

    public static class Builder {

        private EndpointSpec spec = new EndpointSpec();

        public Builder withVipMode() {
            spec.mode = RESOLUTION_MODE_VIP;
            return this;
        }

        public Builder withDnsrrMode() {
            spec.mode = RESOLUTION_MODE_DNSRR;
            return this;
        }

        public Builder withPorts(PortConfig[] ports) {
            spec.ports = ImmutableList.copyOf(ports);
            return this;
        }

        public EndpointSpec build() {
            return spec;
        }
    }

    public static EndpointSpec.Builder builder() {
        return new EndpointSpec.Builder();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final EndpointSpec that = (EndpointSpec) o;

        return Objects.equals(this.mode, that.mode) && Objects.equals(this.ports, that.ports);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mode, ports);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("mode", mode).add("ports", ports).toString();
    }
}
