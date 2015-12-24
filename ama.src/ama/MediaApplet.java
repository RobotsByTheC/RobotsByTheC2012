/*     */ package ama;
/*     */ 
/*     */ import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.TitledBorder;
/*     */ 
/*     */ public class MediaApplet extends JApplet
/*     */ {
/*     */   private static final String version = "0.91.0";
/*     */   private static final int REQUIRED_VM_MAJOR = 1;
/*     */   private static final int REQUIRED_VM_MINOR = 4;
/*     */   private static final int DEFAULT_IMAGE_PANEL_WIDTH = 320;
/*     */   private static final int DEFAULT_IMAGE_PANEL_HEIGHT = 240;
/*     */   private static final int NUMPANELS = 4;
/*     */   public static final int TOP = 0;
/*     */   public static final int BOTTOM = 1;
/*     */   public static final int LEFT = 2;
/*     */   public static final int RIGHT = 3;
/* 119 */   private final String info = getClass().toString() + " version " + "0.91.0";
/* 120 */   private final String DEFAULT_PARAM_CGI_PATH = "operator/param.cgi";
/*     */ 
/* 122 */   private boolean tooOldVM = false;
/* 123 */   private static JFrame frame = null;
/* 124 */   private Hashtable parameters = null;
/*     */   private JDesktopPane desktopPane;
/* 126 */   private ImagePanel imagePanel = null;
/* 127 */   private JPanel clickPanel = null;
/* 128 */   private JPopupMenu popupMenu = null;
/* 129 */   private URL videoURL = null;
/*     */   private String wwwRoot;
/*     */   private JLabel fpsLabel;
/*     */   private JButton playButton;
/*     */   private JButton stopButton;
/*     */   private JMenuItem playMenuButton;
/*     */   private JMenuItem stopMenuButton;
/*     */   private String cgiPath;
/* 137 */   private JComponent[] panels = new JComponent[4];
/* 138 */   private int[] componentCounts = new int[4];
/* 139 */   private Vector plugins = new Vector();
/* 140 */   private Hashtable CGIParameters = new Hashtable();
/*     */ 
/*     */   public MediaApplet()
/*     */   {
/* 153 */     if (frame != null)
/* 154 */       Authenticator.setDefault(new RootPassAuthenticator(null));
/*     */   }
/*     */ 
/*     */   private Plugin loadPlugin(String paramString)
/*     */     throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException
/*     */   {
/* 163 */     System.out.println("Loading plug-in: " + paramString);
/* 164 */     Constructor localConstructor = Class.forName(paramString).getConstructor(new Class[0]);
/*     */ 
/* 166 */     return (Plugin)localConstructor.newInstance(new Object[0]);
/*     */   }
/*     */ 
/*     */   public JPopupMenu getPopupMenu()
/*     */   {
/* 175 */     return this.popupMenu;
/*     */   }
/*     */ 
/*     */   public String getCGIParameter(String paramString)
/*     */   {
/* 190 */     return (String)this.CGIParameters.get(paramString);
/*     */   }
/*     */ 
/*     */   public String buildURL(String paramString1, String paramString2, Hashtable paramHashtable)
/*     */   {
/* 208 */     String str1 = getParameter(paramString1);
/* 209 */     if (str1 == null) {
/* 210 */       str1 = "/" + this.cgiPath + "/" + paramString2;
/*     */     }
/* 212 */     if (paramHashtable == null) {
/* 213 */       return this.wwwRoot + "/" + str1;
/*     */     }
/* 215 */     String str2 = "";
/* 216 */     Enumeration localEnumeration = paramHashtable.keys();
/* 217 */     while (localEnumeration.hasMoreElements()) {
/* 218 */       String str3 = (String)localEnumeration.nextElement();
/* 219 */       String str4 = getCGIParameter(str3);
/* 220 */       if (str4 != null) {
/* 221 */         str2 = str2 + paramHashtable.get(str3) + "=" + str4;
/*     */       }
/* 223 */       if (localEnumeration.hasMoreElements()) {
/* 224 */         str2 = str2 + "&";
/*     */       }
/*     */     }
/* 227 */     if (str1.indexOf('?') == -1)
/* 228 */       str1 = str1 + "?" + str2;
/*     */     else {
/* 230 */       str1 = str1 + "&" + str2;
/*     */     }
/* 232 */     return this.wwwRoot + "/" + str1;
/*     */   }
/*     */ 
/*     */   public void addComponent(JComponent paramJComponent, int paramInt)
/*     */   {
/* 250 */     if (this.panels[paramInt] == null) {
/* 251 */       this.panels[paramInt] = paramJComponent;
/*     */     } else {
/* 253 */       if (this.componentCounts[paramInt] == 1) {
/* 254 */         JTabbedPane localJTabbedPane = new JTabbedPane();
/* 255 */         localJTabbedPane.add(this.panels[paramInt]);
/* 256 */         this.panels[paramInt] = localJTabbedPane;
/*     */       }
/* 258 */       this.panels[paramInt].add(paramJComponent);
/*     */     }
/* 260 */     this.componentCounts[paramInt] += 1;
/*     */   }
/*     */ 
/*     */   public void addOverlay(JComponent paramJComponent)
/*     */   {
/* 271 */     this.desktopPane.add(paramJComponent, JDesktopPane.DEFAULT_LAYER);
/* 272 */     this.desktopPane.moveToFront(paramJComponent);
/*     */   }
/*     */ 
/*     */   public JComponent getClickPanel()
/*     */   {
/* 282 */     return this.clickPanel;
/*     */   }
/*     */ 
/*     */   public JDesktopPane getDesktopPane()
/*     */   {
/* 292 */     return this.desktopPane;
/*     */   }
/*     */ 
/*     */   public void setParameter(String paramString1, String paramString2)
/*     */   {
/* 303 */     if (this.parameters == null) {
/* 304 */       this.parameters = new Hashtable();
/*     */     }
/* 306 */     this.parameters.put(paramString1, paramString2);
/*     */   }
/*     */ 
/*     */   public String getParameter(String paramString)
/*     */   {
/* 316 */     if (this.parameters != null) {
/* 317 */       return (String)this.parameters.get(paramString);
/*     */     }
/* 319 */     return super.getParameter(paramString);
/*     */   }
/*     */ 
/*     */   public String getAppletInfo()
/*     */   {
/* 328 */     return this.info;
/*     */   }
/*     */   public void init() {
/* 335 */     System.out.println("INIT " + this.info);
/*     */     Object localObject1;
/*     */     try {
/* 338 */       String str1 = System.getProperty("java.vm.version").replaceFirst("^[^0-9]+", "");
/*     */ 
/* 340 */       StringTokenizer localStringTokenizer1 = new StringTokenizer(str1, ".");
/* 341 */       int k = Integer.parseInt(localStringTokenizer1.nextToken());
/* 342 */       int m = Integer.parseInt(localStringTokenizer1.nextToken());
/* 343 */       System.out.println("VM version: " + k + "." + m);
/* 344 */       this.tooOldVM = ((k < 1) || (m < 4));
/*     */ 
/* 347 */       if (this.tooOldVM) {
/* 348 */         localObject1 = new JPanel();
/* 349 */         System.err.println("Required VM version: 1.4");
/*     */ 
/* 351 */         ((JPanel)localObject1).add(new JLabel("Your Java VM is too old!"));
/* 352 */         ((JPanel)localObject1).add(new JLabel("Installed Java version: " + str1));
/* 353 */         ((JPanel)localObject1).add(new JLabel("Required Java version: >=1.4"));
/*     */ 
/* 355 */         getContentPane().add((Component)localObject1);
/*     */ 
/* 357 */         return;
/*     */       }
/*     */     } catch (Exception localException1) {
/* 360 */       localException1.printStackTrace(System.err);
/*     */     }
/*     */ 
/* 368 */     setCGIParameters();
/*     */ 
/* 370 */     int i = 320;
/* 371 */     int j = 240;
/* 372 */     String str2 = getParameter("ama_url");
/* 373 */     if (str2 != null) {
/* 374 */       String str3 = (String)this.CGIParameters.get("resolution");
/* 375 */       if ((str3 != null) && (!str3.equals(str2))) {
/* 376 */         localObject1 = new StringTokenizer(str3, "x");
/*     */         try {
/* 378 */           i = Integer.parseInt(((StringTokenizer)localObject1).nextToken());
/* 379 */           j = Integer.parseInt(((StringTokenizer)localObject1).nextToken());
/*     */         } catch (RuntimeException localRuntimeException) {
/* 381 */           System.err.println("Unknown resolution: \"" + str3 + "\"! Using default.");
/*     */ 
/* 383 */           i = 320;
/* 384 */           j = 240;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 389 */     String str3 = getCGIParameter("camera");
/* 390 */     if (str3 == null) {
/* 391 */       this.CGIParameters.put("camera", "1");
/*     */     }
/*     */     try
/*     */     {
/* 395 */       if ((str2 == null) || (str2.startsWith("/"))) {
/* 396 */         localObject1 = getDocumentBase();
/* 397 */         this.wwwRoot = (((URL)localObject1).getProtocol() + "://" + ((URL)localObject1).getHost() + (((URL)localObject1).getPort() == -1 ? "" : new StringBuffer().append(":").append(((URL)localObject1).getPort()).toString()));
/*     */ 
/* 400 */         if (str2 != null)
/* 401 */           this.videoURL = new URL(this.wwwRoot + str2);
/*     */         else
/* 403 */           this.videoURL = new URL(this.wwwRoot);
/*     */       }
/*     */       else {
/* 406 */         this.videoURL = new URL(str2);
/* 407 */         this.wwwRoot = (this.videoURL.getProtocol() + "://" + this.videoURL.getHost() + (this.videoURL.getPort() == -1 ? "" : new StringBuffer().append(":").append(this.videoURL.getPort()).toString()));
/*     */       }
/*     */ 
/* 412 */       this.cgiPath = getParameter("ama_cgi-path");
/* 413 */       if (this.cgiPath == null) {
/* 414 */         this.cgiPath = "axis-cgi";
/*     */       }
/*     */ 
/* 417 */       if (this.videoURL.getPath().length() < 2) {
/* 418 */         this.videoURL = new URL(this.wwwRoot + "/" + this.cgiPath + "/mjpg/video.cgi?showlength=1");
/*     */       }
/*     */ 
/* 423 */       localObject1 = getParameter("ama_param-path");
/* 424 */       if (localObject1 == null) {
/* 425 */         localObject1 = "/" + this.cgiPath + "/" + "operator/param.cgi";
/*     */       }
/* 427 */       ParamTool.setDefaultURL(this.wwwRoot + "/" + (String)localObject1);
/*     */     } catch (MalformedURLException localMalformedURLException) {
/* 429 */       localMalformedURLException.printStackTrace(System.err);
/* 430 */       return;
/*     */     }
/*     */ 
/* 433 */     Object localObject2 = new ActionListener() {
/*     */   @Override
       public void actionPerformed(ActionEvent paramActionEvent) {
/* 435 */         MediaApplet.this.start();
/*     */       }
/*     */     };
/* 438 */     this.playButton = new JButton("Play");
/* 439 */     this.playButton.addActionListener((ActionListener)localObject2);
/* 440 */     this.playMenuButton = new JMenuItem("Play");
/* 441 */     this.playMenuButton.addActionListener((ActionListener)localObject2);
/*     */ 
/* 443 */     localObject2 = new ActionListener() {
/*     */       public void actionPerformed(ActionEvent paramActionEvent) {
/* 445 */         MediaApplet.this.stop();
/* 446 */         MediaApplet.this.fpsLabel.setText("0 fps");
/*     */       }
/*     */     };
/* 449 */     this.stopButton = new JButton("Stop");
/* 450 */     this.stopButton.addActionListener((ActionListener)localObject2);
/* 451 */     this.stopMenuButton = new JMenuItem("Stop");
/* 452 */     this.stopMenuButton.addActionListener((ActionListener)localObject2);
/* 453 */     ImagePanelObserver local3 = new ImagePanelObserver() {
/*     */       public void updateFPS(int paramInt) {
/* 455 */         MediaApplet.this.fpsLabel.setText("" + paramInt + " fps");
/*     */       }
/*     */     };
/* 459 */     this.desktopPane = new JDesktopPane() {
/*     */       public Dimension getMinimumSize() {
/* 461 */         return MediaApplet.this.imagePanel.getSize();
/*     */       }
/*     */ 
/*     */       public Dimension getMaximumSize() {
/* 465 */         return MediaApplet.this.imagePanel.getSize();
/*     */       }
/*     */ 
/*     */       public Dimension getPreferredSize() {
/* 469 */         return MediaApplet.this.imagePanel.getSize();
/*     */       }
/*     */     };
/* 473 */     String str4 = getParameter("ama_zoom");
/*     */     float f;
/*     */     boolean bool;
/* 476 */     if (str4 == null) {
/* 477 */       f = 1.0F;
/* 478 */       bool = false;
/* 479 */     } else if (str4.equals("auto")) {
/* 480 */       f = 0.0F;
/* 481 */       bool = false;
/* 482 */     } else if (str4.equals("free")) {
/* 483 */       f = 0.0F;
/* 484 */       bool = true;
/*     */     } else {
/*     */       try {
/* 487 */         f = Float.parseFloat(str4);
/*     */       } catch (NumberFormatException localNumberFormatException) {
/* 489 */         if (frame == null) {
/* 490 */           localNumberFormatException.printStackTrace();
/*     */         } else {
/* 492 */           System.err.println("Invalid zoom: " + str4);
/* 493 */           printUsageAndExit(1);
/*     */         }
/* 495 */         f = 1.0F;
/*     */       }
/* 497 */       bool = false;
/*     */     }
/* 499 */     int n = f == 0.0F ? 1 : 0;
/* 500 */     System.out.println("Z: " + f);
/*     */ 
/* 502 */     this.imagePanel = new ImagePanel(this.desktopPane, i, j, this.videoURL, f, local3);
/*     */ 
/* 504 */     this.desktopPane.add(this.imagePanel, JDesktopPane.DEFAULT_LAYER);
/*     */ 
/* 506 */     this.fpsLabel = new JLabel("00 fps", 4);
/* 507 */     Dimension localDimension = this.fpsLabel.getPreferredSize();
/* 508 */     this.fpsLabel.setMinimumSize(localDimension);
/* 509 */     this.fpsLabel.setPreferredSize(localDimension);
/* 510 */     this.fpsLabel.setMaximumSize(localDimension);
/* 511 */     JLabel local5 = new JLabel() {
/*     */       public Dimension getMinimumSize() {
/* 513 */         return MediaApplet.this.fpsLabel.getMinimumSize();
/*     */       }
/*     */       public Dimension getPreferredSize() {
/* 516 */         return MediaApplet.this.fpsLabel.getPreferredSize();
/*     */       }
/*     */       public Dimension getMaximumSize() {
/* 519 */         return MediaApplet.this.fpsLabel.getMaximumSize();
/*     */       }
/*     */     };
/* 523 */     String str5 = getParameter("ama_hide-video-controls");
/* 524 */     if ((str5 == null) || (!str5.equals("yes"))) {
/* 525 */       Object localObject3 = new JPanel();
/* 526 */       ((JPanel)localObject3).setLayout(new BoxLayout((Container)localObject3, 0));
/* 527 */       ((JPanel)localObject3).add(local5);
/* 528 */       ((JPanel)localObject3).add(Box.createHorizontalGlue());
/* 529 */       ((JPanel)localObject3).add(this.stopButton);
/* 530 */       ((JPanel)localObject3).add(this.playButton);
/* 531 */       ((JPanel)localObject3).add(Box.createHorizontalGlue());
/* 532 */       ((JPanel)localObject3).add(this.fpsLabel);
/*     */ 
/* 534 */       ((JPanel)localObject3).setName("Video");
/* 535 */       addComponent((JComponent)localObject3, 1);
/*     */     }
/*     */ 
/* 538 */     this.popupMenu = new JPopupMenu();
/* 539 */     this.popupMenu.add(this.stopMenuButton);
/* 540 */     this.popupMenu.add(this.playMenuButton);
/* 541 */     this.clickPanel = new JPanel();
/* 542 */     this.clickPanel.setSize(i, j);
/* 543 */     this.clickPanel.setOpaque(false);
/* 544 */     this.clickPanel.addMouseListener(new MouseAdapter() {
/*     */       public void maybeShowPopup(MouseEvent paramMouseEvent) {
/* 546 */         if (paramMouseEvent.isPopupTrigger())
/* 547 */           MediaApplet.this.popupMenu.show(paramMouseEvent.getComponent(), paramMouseEvent.getX(), paramMouseEvent.getY());
/*     */       }
/*     */ 
/*     */       public void mousePressed(MouseEvent paramMouseEvent)
/*     */       {
/* 552 */         maybeShowPopup(paramMouseEvent);
/*     */       }
/*     */ 
/*     */       public void mouseReleased(MouseEvent paramMouseEvent) {
/* 556 */         maybeShowPopup(paramMouseEvent);
/*     */       }
/*     */ 
/*     */       public void mouseClicked(MouseEvent paramMouseEvent) {
/* 560 */         maybeShowPopup(paramMouseEvent);
/* 561 */         if ((paramMouseEvent.getButton() == 1) && (MediaApplet.this.getCGIParameter("camera").equals("quad")) && (MediaApplet.frame == null))
/*     */         {
/* 565 */           int i = paramMouseEvent.getX();
/* 566 */           int j = paramMouseEvent.getY();
/* 567 */           int k = MediaApplet.this.imagePanel.getWidth();
/* 568 */           int m = MediaApplet.this.imagePanel.getHeight();
/*     */           int n;
/* 570 */           if (i < k / 2) {
/* 571 */             if (j < m / 2)
/* 572 */               n = 1;
/*     */             else {
/* 574 */               n = 3;
/*     */             }
/*     */           }
/* 577 */           else if (j < m / 2)
/* 578 */             n = 2;
/*     */           else {
/* 580 */             n = 4;
/*     */           }
/*     */           try
/*     */           {
/* 584 */             String str = MediaApplet.this.getParameter("ama_camera" + n + "-path");
/*     */ 
/* 586 */             if (str != null) {
/* 587 */               MediaApplet.this.getAppletContext().showDocument(new URL(MediaApplet.this.wwwRoot + "/" + str), "_self");
/*     */             }
/*     */             else {
/* 590 */               MediaApplet.this.getAppletContext().showDocument(new URL(MediaApplet.this.wwwRoot + "/view/view.shtml?imagepath=/mjpg/" + n + "/video.mjpg"), "_self");
/*     */             }
/*     */ 
/*     */           }
/*     */           catch (MalformedURLException localMalformedURLException)
/*     */           {
/* 596 */             localMalformedURLException.printStackTrace(System.err);
/*     */           }
/*     */         }
/*     */       }
/*     */     });
/* 602 */     Object localObject3 = getParameter("ama_plugins");
/* 603 */     if (localObject3 != null) {
/* 604 */       StringTokenizer localStringTokenizer2 = new StringTokenizer((String)localObject3, ",");
/* 605 */       while (localStringTokenizer2.hasMoreTokens()) {
/*     */         try {
/* 607 */           Plugin localPlugin = loadPlugin(localStringTokenizer2.nextToken());
/* 608 */           localPlugin.init(this);
/* 609 */           this.plugins.add(localPlugin);
/*     */         } catch (Exception localException2) {
/* 611 */           localException2.printStackTrace(System.err);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 616 */     for (int i1 = 0; i1 < 4; i1++) {
/* 617 */       if (this.componentCounts[i1] == 1) {
/* 618 */         this.panels[i1].setBorder(new TitledBorder(BorderFactory.createEtchedBorder(), this.panels[i1].getName()));
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 624 */     addOverlay(this.clickPanel);
/*     */     Object localObject4;
/* 627 */     if (bool) {
/* 628 */       localObject4 = this.desktopPane;
/*     */     } else {
/* 630 */       localObject4 = new JPanel();
/* 631 */       ((Container)localObject4).setLayout(new GridBagLayout());
/* 632 */       ((Container)localObject4).add(this.desktopPane);
/* 633 */       if (n != 0) {
/* 634 */         ((Container)localObject4).setBackground(Color.BLACK);
/*     */       }
/*     */     }
/*     */ 
/* 638 */     if (n != 0)
/* 639 */       ((Container)localObject4).addComponentListener(new ComponentAdapter() { 
                  private final boolean val$noAspectRatio = true;
/*     */         private final Container val$centerPanel = new Container();
/*     */ 
/* 641 */         public void componentResized(ComponentEvent paramComponentEvent) { if (this.val$noAspectRatio) {
/* 642 */             MediaApplet.this.imagePanel.setSize(this.val$centerPanel.getSize());
/*     */           } else {
/* 644 */             Dimension localDimension = MediaApplet.this.imagePanel.getImageSize();
/* 645 */             if ((localDimension.getWidth() == 0.0D) || (localDimension.getHeight() == 0.0D)) {
/* 646 */               localDimension = MediaApplet.this.imagePanel.getSize();
/*     */             }
/* 648 */             double d = localDimension.getWidth() / localDimension.getHeight();
/* 649 */             int i = this.val$centerPanel.getWidth();
/* 650 */             int j = this.val$centerPanel.getHeight();
/*     */             int k;
/*     */             int m;
/* 653 */             if (i / j > d) {
/* 654 */               k = (int)(j * d);
/* 655 */               m = j;
/*     */             } else {
/* 657 */               k = i;
/* 658 */               m = (int)(i / d);
/*     */             }
/* 660 */             MediaApplet.this.imagePanel.setSize(k, m);
/*     */           }
/* 662 */           MediaApplet.this.clickPanel.setSize(MediaApplet.this.imagePanel.getSize());
/* 663 */           MediaApplet.this.desktopPane.setSize(MediaApplet.this.imagePanel.getSize());
/* 664 */           this.val$centerPanel.validate(); }
/*     */       });
/*     */     else {
/* 668 */       this.imagePanel.addComponentListener(new ComponentAdapter() { 
                private final Container val$centerPanel = new Container(); 
/* 670 */         public void componentResized(ComponentEvent paramComponentEvent) { MediaApplet.this.clickPanel.setSize(MediaApplet.this.imagePanel.getSize());
/* 671 */           this.val$centerPanel.validate();
/* 672 */           if (MediaApplet.frame != null) {
/* 673 */             MediaApplet.frame.pack();
/*     */           }
/*     */         }
/*     */       });
/*     */     }
/* 679 */     Container localContainer = getContentPane();
/* 680 */     localContainer.setLayout(new GridBagLayout());
/* 681 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/*     */ 
/* 683 */     if (this.panels[2] != null) {
/* 684 */       localGridBagConstraints.gridx = 0;
/* 685 */       localGridBagConstraints.gridy = 0;
/* 686 */       localGridBagConstraints.gridwidth = 1;
/* 687 */       localGridBagConstraints.gridheight = 3;
/* 688 */       localGridBagConstraints.weighty = 1.0D;
/* 689 */       if (n != 0) {
/* 690 */         localGridBagConstraints.fill = 3;
/* 691 */         localGridBagConstraints.weightx = 0.0D;
/*     */       } else {
/* 693 */         localGridBagConstraints.fill = 1;
/* 694 */         localGridBagConstraints.weightx = 0.5D;
/*     */       }
/* 696 */       localContainer.add(this.panels[2], localGridBagConstraints);
/*     */     }
/*     */ 
/* 699 */     if (this.panels[0] != null) {
/* 700 */       localGridBagConstraints.gridx = 1;
/* 701 */       localGridBagConstraints.gridy = 0;
/* 702 */       localGridBagConstraints.gridwidth = 1;
/* 703 */       localGridBagConstraints.gridheight = 1;
/* 704 */       if (n != 0) {
/* 705 */         localGridBagConstraints.fill = 2;
/* 706 */         localGridBagConstraints.weightx = 1.0D;
/* 707 */         localGridBagConstraints.weighty = 0.0D;
/*     */       } else {
/* 709 */         localGridBagConstraints.fill = 1;
/* 710 */         localGridBagConstraints.weightx = 0.0D;
/* 711 */         localGridBagConstraints.weighty = 0.5D;
/*     */       }
/* 713 */       localContainer.add(this.panels[0], localGridBagConstraints);
/*     */     }
/*     */ 
/* 716 */     localGridBagConstraints.gridx = 1;
/* 717 */     localGridBagConstraints.gridy = 1;
/* 718 */     localGridBagConstraints.gridwidth = 1;
/* 719 */     localGridBagConstraints.gridheight = 1;
/* 720 */     if (n != 0) {
/* 721 */       localGridBagConstraints.fill = 1;
/* 722 */       localGridBagConstraints.weightx = 1.0D;
/* 723 */       localGridBagConstraints.weighty = 1.0D;
/*     */     } else {
/* 725 */       localGridBagConstraints.fill = 0;
/* 726 */       localGridBagConstraints.weightx = 0.0D;
/* 727 */       localGridBagConstraints.weighty = 0.0D;
/*     */     }
/* 729 */     localContainer.add((Component)localObject4, localGridBagConstraints);
/*     */ 
/* 731 */     if (this.panels[1] != null) {
/* 732 */       localGridBagConstraints.gridx = 1;
/* 733 */       localGridBagConstraints.gridy = 2;
/* 734 */       localGridBagConstraints.gridwidth = 1;
/* 735 */       localGridBagConstraints.gridheight = 1;
/* 736 */       if (n != 0) {
/* 737 */         localGridBagConstraints.fill = 2;
/* 738 */         localGridBagConstraints.weightx = 1.0D;
/* 739 */         localGridBagConstraints.weighty = 0.0D;
/*     */       } else {
/* 741 */         localGridBagConstraints.fill = 1;
/* 742 */         localGridBagConstraints.weightx = 0.0D;
/* 743 */         localGridBagConstraints.weighty = 0.5D;
/*     */       }
/* 745 */       localContainer.add(this.panels[1], localGridBagConstraints);
/*     */     }
/*     */ 
/* 748 */     if (this.panels[3] != null) {
/* 749 */       localGridBagConstraints.gridx = 2;
/* 750 */       localGridBagConstraints.gridy = 0;
/* 751 */       localGridBagConstraints.gridwidth = 1;
/* 752 */       localGridBagConstraints.gridheight = 3;
/* 753 */       localGridBagConstraints.weighty = 1.0D;
/* 754 */       if (n != 0) {
/* 755 */         localGridBagConstraints.fill = 3;
/* 756 */         localGridBagConstraints.weightx = 0.0D;
/*     */       } else {
/* 758 */         localGridBagConstraints.fill = 1;
/* 759 */         localGridBagConstraints.weightx = 0.5D;
/*     */       }
/* 761 */       localContainer.add(this.panels[3], localGridBagConstraints);
/*     */     }
/*     */ 
/* 764 */     setVisible(true);
/*     */ 
/* 766 */     new Thread(this.imagePanel).start();
/*     */   }
/*     */ 
/*     */   public synchronized void start()
/*     */   {
/* 773 */     if (this.tooOldVM) {
/* 774 */       System.out.println("START refused");
/* 775 */       return;
/*     */     }
/*     */ 
/* 778 */     System.out.println("START");
/* 779 */     this.playButton.setEnabled(false);
/* 780 */     this.stopButton.setEnabled(true);
/* 781 */     this.playMenuButton.setEnabled(false);
/* 782 */     this.stopMenuButton.setEnabled(true);
/* 783 */     this.imagePanel.setStopped(false);
/*     */ 
/* 785 */     Enumeration localEnumeration = this.plugins.elements();
/* 786 */     while (localEnumeration.hasMoreElements())
/* 787 */       ((Plugin)localEnumeration.nextElement()).start();
/*     */   }
/*     */ 
/*     */   public synchronized void stop()
/*     */   {
/* 795 */     System.out.println("STOP");
/* 796 */     this.playButton.setEnabled(true);
/* 797 */     this.stopButton.setEnabled(false);
/* 798 */     this.playMenuButton.setEnabled(true);
/* 799 */     this.stopMenuButton.setEnabled(false);
/* 800 */     if (!this.tooOldVM) {
/* 801 */       this.imagePanel.setStopped(true);
/*     */     }
/*     */ 
/* 804 */     Enumeration localEnumeration = this.plugins.elements();
/* 805 */     while (localEnumeration.hasMoreElements())
/* 806 */       ((Plugin)localEnumeration.nextElement()).stop();
/*     */   }
/*     */ 
/*     */   public synchronized void destroy()
/*     */   {
/*     */     try
/*     */     {
/* 816 */       Thread.currentThread(); Thread.sleep(1000L);
/*     */     } catch (InterruptedException localInterruptedException) {
/* 818 */       System.err.println("Interrupted in sleep");
/*     */     }
/* 820 */     System.out.println("DESTROY");
/*     */   }
/*     */ 
/*     */   private void setCGIParameters() {
/* 824 */     String str1 = getParameter("ama_url");
/* 825 */     String str2 = str1.replaceFirst(".*[?](.*[=].*)", "$1");
/* 826 */     if (!str2.equals(str1)) {
/* 827 */       StringTokenizer localStringTokenizer = new StringTokenizer(str2, "&=");
/* 828 */       String str3 = null;
/* 829 */       String str4 = null;
/* 830 */       while (localStringTokenizer.hasMoreElements()) {
/* 831 */         str3 = localStringTokenizer.nextToken();
/* 832 */         str4 = localStringTokenizer.nextToken();
/* 833 */         if ((str3 != null) && (str4 != null))
/* 834 */           this.CGIParameters.put(str3, str4);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void parseOptions(String[] paramArrayOfString)
/*     */   {
/* 841 */     if (paramArrayOfString.length < 1) {
/* 842 */       printUsageAndExit(1);
/*     */     }
/*     */ 
/* 845 */     setParameter("ama_password", "pass");
/* 846 */     setParameter("ama_user", "root");
/*     */ 
/* 848 */     for (int i = 0; i < paramArrayOfString.length; i++) {
/* 849 */       if (paramArrayOfString[i].startsWith("--")) {
/* 850 */         if (i == paramArrayOfString.length - 1) {
/* 851 */           printUsageAndExit(1);
/*     */         }
/*     */ 
/* 854 */         if ((paramArrayOfString[i].equals("--help")) || (paramArrayOfString[i].equals("--hide-video-controls")))
/*     */         {
/* 857 */           setParameter("ama_" + paramArrayOfString[i].substring(2), "yes");
/* 858 */         } else if ((paramArrayOfString[i].equals("--cgi-path")) || (paramArrayOfString[i].equals("--param-path")) || (paramArrayOfString[i].equals("--plugins")) || (paramArrayOfString[i].equals("--password")) || (paramArrayOfString[i].equals("--user")) || (paramArrayOfString[i].equals("--zoom")))
/*     */         {
/* 864 */           if ((i == paramArrayOfString.length - 2) || (paramArrayOfString[(i + 1)].startsWith("--")))
/*     */           {
/* 867 */             System.err.println("Option '" + paramArrayOfString[i] + "' " + "requires an argument");
/*     */ 
/* 869 */             printUsageAndExit(1);
/*     */           }
/* 871 */           setParameter("ama_" + paramArrayOfString[i].substring(2), paramArrayOfString[(i + 1)]);
/* 872 */           i++;
/* 873 */         } else if (getParameter("ama_plugins") != null) {
/* 874 */           System.out.println("Unrecognized option '" + paramArrayOfString[i] + "' will be stored for plug-ins to use.");
/*     */           int j;
/* 878 */           if (i == paramArrayOfString.length - 2)
/* 879 */             j = 0;
/* 880 */           else if (paramArrayOfString[(i + 1)].startsWith("--"))
/* 881 */             j = 0;
/*     */           else {
/* 883 */             j = 1;
/*     */           }
/*     */ 
/* 886 */           if (j != 0) {
/* 887 */             setParameter("ama_" + paramArrayOfString[i].substring(2), paramArrayOfString[(i + 1)]);
/*     */ 
/* 889 */             i++;
/*     */           } else {
/* 891 */             setParameter("ama_" + paramArrayOfString[i].substring(2), "yes");
/*     */           }
/*     */         } else {
/* 894 */           System.err.println("Invalid option: '" + paramArrayOfString[i] + "'");
/* 895 */           printUsageAndExit(1);
/*     */         }
/*     */       } else {
/* 897 */         if (i == paramArrayOfString.length - 1) {
/* 898 */           setParameter("ama_url", paramArrayOfString[i]);
/* 899 */           return;
/*     */         }
/* 901 */         System.err.println("Invalid argument: '" + paramArrayOfString[i] + "'");
/* 902 */         printUsageAndExit(1);
/*     */       }
/*     */     }
/* 905 */     printUsageAndExit(1);
/*     */   }
/*     */ 
/*     */   private static void printUsageAndExit(int paramInt) {
/* 909 */     PrintStream localPrintStream = paramInt == 0 ? System.out : System.err;
/*     */ 
/* 911 */     localPrintStream.println("Usage: [OPTIONS] URL\n\nOptions:\n    --cgi-path             relative path to CGI directory\n    --param-path           relative path to parameters supported by the device\n    --help                 show this help and exit\n    --hide-video-controls  do not show the video control panel\n    --password PASSWD      http-password, default is 'pass'\n    --plugins P1[,P2...]]  list of plug-ins to load\n    --user NAME            http-user, default is 'root'\n    --zoom R|auto|free     zoom rate:\n                               R:    zoom R times, e.g '0.5' or '2'\n                               auto: zoom to fit, preserve aspect ratio\n                               free: zoom to fit, no aspect ratio\n                           default is '1x'\n\nNote: when using --plugins, any other options will be accepted as well.");
/*     */ 
/* 929 */     System.exit(paramInt);
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*     */     try
/*     */     {
/* 939 */       frame = new JFrame("Live View");
/* 940 */       frame.addWindowListener(new WindowAdapter() {
/*     */         public void windowClosing(WindowEvent paramWindowEvent) {
/* 942 */           System.exit(0);
/*     */         }
/*     */       });
/* 945 */       MediaApplet localMediaApplet = new MediaApplet();
/* 946 */       localMediaApplet.parseOptions(paramArrayOfString);
/* 947 */       if (localMediaApplet.getParameter("ama_help") != null) {
/* 948 */         printUsageAndExit(0);
/*     */       }
/* 950 */       frame.setContentPane(localMediaApplet.getContentPane());
/* 951 */       localMediaApplet.init();
/* 952 */       frame.pack();
/* 953 */       frame.setVisible(true);
/* 954 */       localMediaApplet.start();
/*     */     } catch (Exception localException) {
/* 956 */       localException.printStackTrace(System.err);
/*     */     }
/*     */   }
/*     */ 
/*     */   private class RootPassAuthenticator extends Authenticator
/*     */   {
/*     */     private final MediaApplet this$0;
/*     */ 
/*     */     private RootPassAuthenticator()
/*     */     {
/* 142 */       this.this$0 = this$1;
/*     */     }
/* 144 */     protected PasswordAuthentication getPasswordAuthentication() { return new PasswordAuthentication(this.this$0.getParameter("ama_user"), this.this$0.getParameter("ama_password").toCharArray());
/*     */     }
/*     */ 
/*     */     public RootPassAuthenticator(MediaApplet.1 arg2)
/*     */     {
/* 142 */       this();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Developer\Desktop\ama.jar
 * Qualified Name:     ama.MediaApplet
 * JD-Core Version:    0.6.0
 */