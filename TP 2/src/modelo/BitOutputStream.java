package modelo;

public class BitOutputStream {
    private byte buffer;
    private int ocupado; 
    private byte[] codificacion = new byte[4000];
    private int cantByte = 0;
    
    public BitOutputStream()
    {
        this.buffer = 0;
        this.ocupado = 0;
    }
    
    public void grabarBits(String secuencia)
    {
        int i;
        
        for (i = 0; i < secuencia.length(); i++)
        {
            /* Agrego 1 bit */
            this.buffer <<= 1;
            if (secuencia.charAt(i) == '1')
                this.buffer |= 1;
            this.ocupado++;
            /* Si se llena el buffer, hay que grabar */
            if (this.ocupado == Byte.SIZE)
                this.vaciarBuffer();
        }
    }
    
    private void vaciarBuffer()
    {
        /* relleno bits sobrantes con 0s */
        if (this.ocupado != 0)
        {
            this.buffer <<= (Byte.SIZE - this.ocupado);
            this.codificacion[cantByte] = this.buffer;
            cantByte++;

            /* libero buffer */
            this.buffer = 0;
            this.ocupado = 0;
        }
    }
    
    private void flush()
    {
        this.vaciarBuffer();
    }
    
    public void cerrar()
    {
        this.flush();
    }

    public byte[] getCodificacion() {
        return codificacion;
    }
}
