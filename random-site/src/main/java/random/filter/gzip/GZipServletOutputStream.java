package random.filter.gzip;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Yo on 2015/10/21.
 */
public class GZipServletOutputStream extends ServletOutputStream {

    private OutputStream stream;
    public GZipServletOutputStream(OutputStream outputStream) {
        super();
        this.stream = outputStream;
    }

    @Override
    public void close() throws IOException {
        this.stream.close();
    }

    @Override
    public void flush() throws IOException {
        this.stream.flush();
    }

    @Override
    public void write(byte[] b) throws IOException {
        this.stream.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        this.stream.write(b, off, len);
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }

    @Override
    public void write(int b) throws IOException {
        this.stream.write(b);
    }
}
