/*     */ package ama;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.Toolkit;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import javax.swing.JDesktopPane;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ class ImagePanel extends JPanel
/*     */   implements Runnable
/*     */ {
/*     */   public static final int AUTO = 0;
/*     */   private JDesktopPane desktopPane;
/*     */   private URL url;
/*     */   private ImagePanelObserver observer;
/*     */   private Image visibleImage;
/*     */   private int imageWidth;
/*     */   private int imageHeight;
/*  43 */   private Image drawnImage = null;
/*     */ 
/*  46 */   private boolean stopped = false;
/*     */   private float zoom;
/*  52 */   private boolean resizeToFit = false;
/*     */ 
/*  55 */   private int numDrawn = 0;
/*  56 */   private long time = 0L;
/*  57 */   private long oldTime = 0L;
/*     */ 
/*     */   public ImagePanel(JDesktopPane paramJDesktopPane, int paramInt1, int paramInt2, URL paramURL, float paramFloat, ImagePanelObserver paramImagePanelObserver)
/*     */   {
/*  61 */     this.desktopPane = paramJDesktopPane;
/*  62 */     this.url = paramURL;
/*  63 */     this.zoom = paramFloat;
/*  64 */     this.observer = paramImagePanelObserver;
/*  65 */     this.resizeToFit = (paramFloat == 0.0F);
/*  66 */     setBackground(Color.BLACK);
/*  67 */     setSize(paramInt1, paramInt2);
/*     */   }
/*     */ 
/*     */   public synchronized void paint(Graphics paramGraphics) {
/*  71 */     if (this.visibleImage != null) {
/*  72 */       if (this.resizeToFit)
/*  73 */         paramGraphics.drawImage(this.visibleImage, 0, 0, getWidth(), getHeight(), this);
/*     */       else {
/*  75 */         paramGraphics.drawImage(this.visibleImage, 0, 0, this.imageWidth, this.imageHeight, this);
/*     */       }
/*  77 */       if (this.visibleImage != this.drawnImage) {
/*  78 */         this.drawnImage = this.visibleImage;
/*  79 */         this.numDrawn += 1;
/*     */       }
/*  81 */       this.time = (System.currentTimeMillis() / 1000L);
/*  82 */       if (this.time != this.oldTime) {
/*  83 */         int i = (int)(this.time - this.oldTime);
/*  84 */         if (this.observer != null) {
/*  85 */           this.observer.updateFPS(this.numDrawn / i);
/*     */         }
/*  87 */         this.oldTime = this.time;
/*  88 */         this.numDrawn = 0;
/*     */       }
/*     */     } else {
/*  91 */       paramGraphics.setColor(Color.BLACK);
/*  92 */       paramGraphics.fillRect(0, 0, getWidth(), getHeight());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void update(Graphics paramGraphics) {
/*  97 */     paint(paramGraphics);
/*     */   }
/*     */ 
/*     */   public boolean imageUpdate(Image paramImage, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*     */   {
/* 117 */     if ((paramInt1 & 0x20) != 0) {
/* 118 */       synchronized (this)
/*     */       {
/* 120 */         if ((paramInt4 == 704) && (paramInt5 == 288)) {
/* 121 */           paramInt5 = 576;
/*     */         }
/*     */ 
/* 124 */         if (this.zoom > 0.0F) {
/* 125 */           paramInt4 = (int)(paramInt4 * this.zoom);
/* 126 */           paramInt5 = (int)(paramInt5 * this.zoom);
/*     */         }
/*     */ 
/* 133 */         if ((!this.resizeToFit) && ((paramInt4 != getWidth()) || (paramInt5 != getHeight())))
/*     */         {
/* 136 */           setSize(paramInt4, paramInt5);
/*     */         }
/*     */ 
/* 143 */         this.visibleImage = paramImage;
/* 144 */         this.imageWidth = paramInt4;
/* 145 */         this.imageHeight = paramInt5;
/*     */ 
/* 152 */         notify();
/*     */ 
/* 158 */         repaint();
/*     */       }
/*     */ 
/* 161 */       return false;
/*     */     }
/*     */ 
/* 164 */     return true;
/*     */   }
/*     */ 
/*     */   public synchronized Dimension getImageSize() {
/* 168 */     return new Dimension(this.imageWidth, this.imageHeight);
/*     */   }
/*     */ 
/*     */   public void run() {
/* 172 */     Object localObject1 = new byte[50000];
/* 173 */     Object localObject2 = new byte[50000];
/*     */     while (true)
/*     */     {
/* 177 */       if (isStopped()) {
/*     */         try {
/* 179 */           Thread.currentThread(); Thread.sleep(500L);
/*     */         } catch (InterruptedException localInterruptedException1) {
/* 181 */           System.out.println("Interrupted in sleep");
/* 182 */         }continue;
/*     */       }
/* 184 */       repaint();
/*     */       try
/*     */       {
/* 188 */         URLConnection localURLConnection = this.url.openConnection();
/* 189 */         System.out.println("URL: " + this.url);
/*     */         int i;
                  Object localObject4 = new MJPEGHandler();
/* 208 */         localObject4 = (MJPEGHandler)((MJPEGHandler)localObject4).getContent(localURLConnection);
/*     */         Object localObject5;
/* 190 */         if ((localURLConnection instanceof HttpURLConnection)) {
/* 191 */           localObject4 = (HttpURLConnection)localURLConnection;
/* 192 */           i = ((HttpURLConnection)localObject4).getResponseCode();
/* 193 */           if (((HttpURLConnection)localObject4).usingProxy())
/* 194 */             System.out.println("Using proxy.");
/*     */           else {
/* 196 */             System.out.println("No proxy.");
/*     */           }
/* 198 */           localObject5 = ((HttpURLConnection)localObject4).getResponseMessage();
/* 199 */           System.out.println("Response: " + i + " " + (String)localObject5);
/* 200 */           if (i != 200) {
/* 201 */             if (i == 503) {
/* 202 */               localObject5 = (String)localObject5 + ". There might be too many viewers.";
/*     */             }
/* 204 */             throw new IOException((String)localObject5);
/*     */           }
/*     */         }
/* 207 */         
/*     */ 
/* 211 */         while (!isStopped())
/*     */         {
/* 213 */           i = ((MJPEGHandler)localObject4).getNextContentLength();
/* 214 */           if (i > ((byte[])localObject1).length) {
/* 215 */             localObject1 = new byte[i + i / 5];
/*     */           }
/* 217 */           ((MJPEGHandler)localObject4).read((byte[])localObject1, i);
/*     */ 
/* 219 */           localObject5 = Toolkit.getDefaultToolkit().createImage((byte[])localObject1, 0, i);
/*     */           Object localObject3;
                    localObject3 = localObject2;
/* 246 */           localObject2 = localObject1;
/* 247 */           localObject1 = localObject3;
/* 222 */           synchronized (this)
/*     */           {
/* 228 */             prepareImage((Image)localObject5, -1, -1, this);
/*     */             while (true)
/*     */             {
/*     */               try
/*     */               {
/* 236 */                 wait();
/*     */               }
/*     */               catch (InterruptedException localInterruptedException3) {
/* 239 */                 System.out.println("Interrupted in wait");
/*     */               }
/*     */             }
/*     */ 
/*     */           }
/* 245 */           
/*     */         }
/* 249 */         ((MJPEGHandler)localObject4).getNextContent().close();
/*     */       } catch (IOException localIOException) {
/* 251 */         localIOException.printStackTrace(System.err);
/* 252 */         if (this.desktopPane != null) {
/* 253 */           JOptionPane.showMessageDialog(this.desktopPane, localIOException.getClass().toString() + ": " + localIOException.getMessage(), "Video Error", 0);
/*     */         }
/*     */ 
/* 257 */         synchronized (this) {
/* 258 */           this.visibleImage = null;
/*     */         }
/*     */       } catch (Exception localException) {
/* 261 */         System.err.println("OOPS!");
/* 262 */         localException.printStackTrace(System.err);
/* 263 */         synchronized (this) {
/* 264 */           this.visibleImage = null;
/*     */         }
/*     */       }
/*     */ 
/* 268 */       this.numDrawn = 0;
/* 269 */       repaint();
/*     */       try
/*     */       {
/* 272 */         Thread.currentThread(); Thread.sleep(1000L);
/*     */       } catch (InterruptedException localInterruptedException2) {
/* 274 */         System.out.println("Interrupted in sleep");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean isStopped() {
/* 280 */     return this.stopped;
/*     */   }
/*     */ 
/*     */   public synchronized void setStopped(boolean paramBoolean) {
/* 284 */     this.stopped = paramBoolean;
/*     */   }
/*     */ 
/*     */   public synchronized void setURL(URL paramURL) {
/* 288 */     this.url = paramURL;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*     */     try
/*     */     {
/* 304 */       JFrame localJFrame = new JFrame();
/* 305 */       URL localURL = new URL(paramArrayOfString[0]);
/* 306 */       ImagePanel localImagePanel = new ImagePanel(null, 1024, 768, localURL, 1.0F, new ImagePanelObserver()
/*     */       {
/*     */         public void updateFPS(int paramInt) {
/* 309 */           System.out.println("FPS: " + paramInt);
/*     */         }
/*     */       });
/* 312 */       new Thread(localImagePanel).start();
/* 313 */       localJFrame.getContentPane().add(localImagePanel);
/* 314 */       localJFrame.pack();
/* 315 */       localJFrame.setVisible(true);
/*     */     } catch (Exception localException) {
/* 317 */       localException.printStackTrace(System.err);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Developer\Desktop\ama.jar
 * Qualified Name:     ama.ImagePanel
 * JD-Core Version:    0.6.0
 */