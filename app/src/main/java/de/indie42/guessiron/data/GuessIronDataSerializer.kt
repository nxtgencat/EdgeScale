package de.indie42.guessiron.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import de.indie42.guessiron.GuessIronData
import java.io.InputStream
import java.io.OutputStream

object GuessIronDataSerializer : Serializer<GuessIronData> {
    override val defaultValue: GuessIronData = GuessIronData.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): GuessIronData {
        try {
            return GuessIronData.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: GuessIronData, output: OutputStream) = t.writeTo(output)
}