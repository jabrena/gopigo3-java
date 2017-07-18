import com.pi4j.io.spi.SpiChannel;
import com.pi4j.io.spi.SpiDevice;
import com.pi4j.io.spi.SpiFactory;

import java.io.IOException;

public class HelloWorld {

    // SPI device
    public static SpiDevice spi = null;

    // ADC channel count
    public static short ADC_CHANNEL_COUNT = 8;  // MCP3004=4, MCP3008=8

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Hello world");

        // create SPI object instance for SPI for communication
        spi = SpiFactory.getInstance(SpiChannel.CS0,
                SpiDevice.DEFAULT_SPI_SPEED, // default spi speed 1 MHz
                SpiDevice.DEFAULT_SPI_MODE); // default spi mode 0

        byte data[] = new byte[] {
                (byte) 8,                              // first byte, start bit
                (byte) "SET_MOTOR_PWM",    // SET_MOTOR_PWM
                (byte) 0x01,
                (byte) 100, // MOTOR_LEFT  = 0x01 MOTOR_RIGHT = 0x02
        };

        // send conversion request to ADC chip via SPI channel
        byte[] result = spi.write(data);

    }

}
