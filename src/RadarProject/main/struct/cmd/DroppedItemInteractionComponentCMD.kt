@file:Suppress("NAME_SHADOWING")
package main.struct.cmd

import com.badlogic.gdx.math.Vector2
import main.deserializer.channel.ActorChannel.Companion.droppedItemLocation
import main.struct.Actor
import main.struct.Bunch
import main.struct.cmd.CMD.propertyVector
import main.deserializer.channel.ActorChannel.Companion.droppedItemCompToItem
import main.struct.NetGuidCacheObject

object DroppedItemInteractionComponentCMD {
    fun process(actor: Actor, bunch: Bunch, repObj: NetGuidCacheObject?, waitingHandle: Int, data: HashMap<String, Any?>): Boolean {
        with(bunch) {
            when (waitingHandle) {
                1 -> {
                    val bReplicates = readBit()
                    val a = bReplicates
                }
                2 -> {
                    val isAlive = readBit()
                    val a = isAlive
                }
                3 -> {
                    val attachParent = readObject()
                    val a = attachParent
//          println("attachParent:$attachParent")
                }
                4 -> {
                    return false
                }
                5 -> {
                    val attachSocketName = readName()
                    val a = attachSocketName
                }
                6 -> {
                    val bReplicatesAttachmentReference = readBit()
                    val a = bReplicatesAttachmentReference
                }
                7 -> {
                    val bReplicatesAttachment = readBit()
                    val a = bReplicatesAttachment
                }
                8 -> {
                    val bAbsoluteLocation = readBit()
                    val a = bAbsoluteLocation
                }
                9 -> {
                    val bAbsoluteRotation = readBit()
                    val a = bAbsoluteRotation
                }
                10 -> {
                    val bAbsoluteScale = readBit()
                    val a = bAbsoluteScale
                }
                11 -> {
                    val bVisible = readBit()
                    val a = bVisible
                }
                12 -> {
                    val relativeLocation = propertyVector()
                    data["relativeLocation"] = Vector2(relativeLocation.x, relativeLocation.y)
//          println("relativeLocation:$relativeLocation")
                }
                13 -> {
                    val relativeRotation = readRotationShort()
                    data["relativeRotation"] = relativeRotation
//          println("relativeRotation:$relativeRotation")
                }
                14 -> {
                    val relativeScale3D = propertyVector()
                    val a = relativeScale3D
                }
                15 -> {
                    val (itemGUID, _) = readObject()
                    val (loc, _) = droppedItemLocation[itemGUID] ?: return true
                    droppedItemCompToItem[repObj!!.outerGUID] = itemGUID
//          print("item loc:$loc ->")
                    loc.add(data["relativeLocation"] as Vector2)
//          println("item loc:$loc")
                }
                else -> return false
            }
        }
        return true
    }
}