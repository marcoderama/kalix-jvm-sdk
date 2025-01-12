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

package com.example.wiring.actions.echo;

import kalix.javasdk.action.Action;
import kalix.javasdk.action.ActionCreationContext;
import kalix.springsdk.KalixClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class ShortenedEchoAction extends Action {

  private ActionCreationContext ctx;
  private KalixClient kalixClient;

  public ShortenedEchoAction(ActionCreationContext ctx, KalixClient kalixClient) {
    this.ctx = ctx;
    this.kalixClient = kalixClient;
  }

  @GetMapping("/echo/message/{msg}/short")
  public Effect<Message> stringMessage(@PathVariable String msg) {
    var shortenedMsg = msg.replaceAll("[AEIOUaeiou]", "");
    var result = kalixClient.get("/echo/message/" + shortenedMsg, Message.class).execute();
    return effects().asyncReply(result);
  }
}
