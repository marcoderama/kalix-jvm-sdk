package com.example.replicated.countermap.domain

import com.akkaserverless.scalasdk.replicatedentity.ReplicatedCounterMap
import com.akkaserverless.scalasdk.replicatedentity.ReplicatedEntity
import com.akkaserverless.scalasdk.replicatedentity.ReplicatedEntityContext
import com.example.replicated.countermap
import com.google.protobuf.empty.Empty

// This class was initially generated based on the .proto definition by Akka Serverless tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

/** A replicated entity. */
class SomeCounterMap(context: ReplicatedEntityContext) extends AbstractSomeCounterMap {

  /** Command handler for "Increase". */
  def increase(currentData: ReplicatedCounterMap[String], increaseValue: countermap.IncreaseValue): ReplicatedEntity.Effect[Empty] =
    effects
      .update(currentData.increment(increaseValue.key, increaseValue.value))
      .thenReply(Empty.defaultInstance)

  /** Command handler for "Decrease". */
  def decrease(currentData: ReplicatedCounterMap[String], decreaseValue: countermap.DecreaseValue): ReplicatedEntity.Effect[Empty] =
    effects
      .update(currentData.decrement(decreaseValue.key, decreaseValue.value))
      .thenReply(Empty.defaultInstance)

  /** Command handler for "Remove". */
  def remove(currentData: ReplicatedCounterMap[String], removeValue: countermap.RemoveValue): ReplicatedEntity.Effect[Empty] =
    effects
      .update(currentData.remove(removeValue.key))
      .thenReply(Empty.defaultInstance)

  /** Command handler for "Get". */
  def get(currentData: ReplicatedCounterMap[String], getValue: countermap.GetValue): ReplicatedEntity.Effect[countermap.CurrentValue] =
    effects
      .reply(countermap.CurrentValue(currentData(getValue.key)))

  /** Command handler for "GetAll". */
  def getAll(currentData: ReplicatedCounterMap[String], getAllValues: countermap.GetAllValues): ReplicatedEntity.Effect[countermap.CurrentValues] = {
    val keyValues = currentData.keySet.map { key => key -> currentData(key) }.toMap
    effects.reply(countermap.CurrentValues(keyValues))
  }

}