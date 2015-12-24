/*     */ package ama;
/*     */ 
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ContentHandler;
import java.net.URLConnection;
/*     */ 
/*     */ class MJPEGHandler extends ContentHandler
/*     */ {
/*     */   private InputStream stream;
/*     */   private String boundary;
/*  15 */   private StringBuffer buffer = new StringBuffer(64);
/*     */ 
/*     */   private byte read() throws IOException {
/*  18 */     int i = this.stream.read();
/*  19 */     if (i == -1) {
/*  20 */       throw new EOFException("End of File");
/*     */     }
/*  22 */     return (byte)i;
/*     */   }
/*     */ 
/*     */   private void readLine() throws IOException {
/*  26 */     this.buffer.delete(0, this.buffer.length());
/*  27 */     int i = read();
/*  28 */     while (i != 13) {
/*  29 */       this.buffer.append((char)i);
/*  30 */       i = read();
/*     */     }
/*  32 */     i = read();
/*  33 */     if (i != 10)
/*  34 */       System.err.println("Expected linefeed (10), got: " + i);
/*     */   }
/*     */ 
/*     */   private void readToken() throws IOException
/*     */   {
/*  39 */     this.buffer.delete(0, this.buffer.length());
/*  40 */     int i = read();
/*  41 */     while (i != 32) {
/*  42 */       this.buffer.append((char)i);
/*  43 */       i = read();
/*     */     }
/*     */   }
/*     */ 
/*     */@Override
   public Object getContent(URLConnection paramURLConnection) throws IOException {
/*  48 */     this.stream = paramURLConnection.getInputStream();
/*     */ 
/*  50 */     String str = paramURLConnection.getContentType();
/*     */ 
/*  52 */     if (!str.matches("^multipart/x-mixed-replace;\\sboundary=.+$"))
/*     */     {
/*  54 */       System.err.println("Wrong content type!");
/*  55 */       return null;
/*     */     }
/*  57 */     this.boundary = str.replaceFirst("^multipart/x-mixed-replace;\\sboundary=", "");
/*     */ 
/*  60 */     return this;
/*     */   }
/*     */ 
/*     */   public int getNextContentLength() throws IOException
/*     */   {
/*  65 */     readLine();
              int i;
/*     */     while (true)
/*     */     {
/*  69 */       i = this.buffer.lastIndexOf(this.boundary);
/*  70 */       if ((i == 0) || ((i == 2) && (this.buffer.lastIndexOf("--") == 0)))
/*     */       {
/*     */         break;
/*     */       }
/*  74 */       readLine();
/*     */     }
/*     */ 
/*  77 */     readLine();
/*  78 */     if (this.buffer.lastIndexOf("Content-Type: image/jpeg") != 0) {
/*  79 */       System.err.println("Expected content type, got: " + this.buffer);
/*  80 */       return -1;
/*     */     }
/*     */ 
/*  83 */     i = 0;
/*  84 */     readToken();
/*  85 */     if (this.buffer.lastIndexOf("Content-Length:") != 0) {
/*  86 */       System.err.println("Expected content length, got: " + this.buffer);
/*  87 */       return -1;
/*     */     }
/*  89 */     readLine();
/*  90 */     int j = this.buffer.length() - 1;
/*  91 */     int k = 1;
/*  92 */     while (j >= 0) {
/*  93 */       i += (this.buffer.charAt(j) - '0') * k;
/*  94 */       j--;
/*  95 */       k *= 10;
/*     */     }
/*     */ 
/*  98 */     return i;
/*     */   }
/*     */ 
/*     */   public InputStream getNextContent() throws IOException {
/* 102 */     while ((this.buffer.length() != 0) || (this.buffer.lastIndexOf("Content-Type: ") == 0))
/*     */     {
/* 104 */       readLine();
/*     */     }
/*     */ 
/* 107 */     return this.stream;
/*     */   }
/*     */ 
/*     */   public void read(byte[] paramArrayOfByte, int paramInt) throws IOException
/*     */   {
/* 112 */     getNextContent();
/* 113 */     int i = 0;
/* 114 */     while (i < paramInt)
/* 115 */       i += this.stream.read(paramArrayOfByte, i, paramInt - i);
/*     */   }
/*     */ }

/* Location:           C:\Users\Developer\Desktop\ama.jar
 * Qualified Name:     ama.MJPEGHandler
 * JD-Core Version:    0.6.0
 */