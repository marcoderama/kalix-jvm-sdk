/*
 * Copyright 2021 Lightbend Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kalix.springsdk.testmodels.action;

import kalix.javasdk.action.Action;
import kalix.springsdk.annotations.Subscribe;
import kalix.springsdk.testmodels.Done;
import kalix.springsdk.testmodels.valueentity.Counter;
import kalix.springsdk.testmodels.valueentity.CounterState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/subscription/log")
public class CounterSubscriber extends Action {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Subscribe.ValueEntity(Counter.class)
  @PostMapping("/counter")
  public Effect<Done> changes(@RequestBody CounterState counterState) {
    logger.info("Counter subscriber: counter id '{}' is '{}'", counterState.id, counterState.value);
    return effects().reply(Done.instance);
  }
}
