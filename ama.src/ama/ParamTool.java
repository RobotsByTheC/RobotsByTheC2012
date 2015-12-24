/*     */ package ama;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ public class ParamTool
/*     */ {
/*     */   private static String defaultURL;
/*     */   private static ParamTool tool;
/*  18 */   private static String defaultEncoding = new InputStreamReader(System.in).getEncoding();
/*     */   private String url;
/*     */ 
/*     */   public static void setDefaultURL(String paramString)
/*     */   {
/*  45 */     defaultURL = paramString;
/*  46 */     tool = new ParamTool(paramString);
/*     */   }
/*     */ 
/*     */   public static ParamTool getDefaultParamTool()
/*     */   {
/*  55 */     return tool;
/*     */   }
/*     */ 
/*     */   private ParamTool(String paramString) {
/*  59 */     this.url = paramString;
/*     */   }
/*     */ 
/*     */   public String addGroup(String paramString1, String paramString2)
/*     */     throws IOException, ParamTool.TooManyGroupsException
/*     */   {
/*  73 */     return addGroup(paramString1, paramString2, "");
/*     */   }
/*     */ 
/*     */   public String addGroup(String paramString1, String paramString2, String paramString3)
/*     */     throws IOException, ParamTool.TooManyGroupsException
/*     */   {
/*     */     URL localURL;
/*     */     try
/*     */     {
/*  90 */       localURL = new URL(this.url + "?action=add&group=" + paramString1 + "&template=" + paramString2 + paramString3);
/*     */     }
/*     */     catch (MalformedURLException localMalformedURLException) {
/*  93 */       localMalformedURLException.printStackTrace(System.err);
/*  94 */       return null;
/*     */     }
/*     */ 
/*  97 */     BufferedReader localBufferedReader = null;
/*  98 */     String str1 = null;
/*     */     try {
/* 100 */       localBufferedReader = new BufferedReader(new InputStreamReader(localURL.openStream()));
/*     */ 
/* 102 */       String str2 = localBufferedReader.readLine();
/* 103 */       while (str2 != null) {
/* 104 */         int i = str2.indexOf(" OK");
/* 105 */         if (i == -1) {
/* 106 */           throw new TooManyGroupsException("Maximum number of " + paramString1 + " groups has been reached.");
/*     */         }
/*     */ 
/* 109 */         str1 = str2.substring(0, str2.indexOf(" OK"));
/* 110 */         str2 = localBufferedReader.readLine();
/*     */       }
/*     */     } finally {
/* 113 */       if (localBufferedReader != null) {
/* 114 */         localBufferedReader.close();
/*     */       }
/*     */     }
/*     */ 
/* 118 */     return str1;
/*     */   }
/*     */ 
/*     */   public Hashtable getGroups(String paramString)
/*     */     throws IOException
/*     */   {
/*     */     URL localURL;
/*     */     try
/*     */     {
/* 131 */       localURL = new URL(this.url + "?action=list&group=" + paramString);
/*     */     } catch (MalformedURLException localMalformedURLException) {
/* 133 */       localMalformedURLException.printStackTrace(System.err);
/* 134 */       return null;
/*     */     }
/*     */ 
/* 137 */     Hashtable localHashtable = new Hashtable();
/* 138 */     BufferedReader localBufferedReader = null;
/*     */     try {
/* 140 */       localBufferedReader = new BufferedReader(new InputStreamReader(localURL.openStream()));
/*     */ 
/* 143 */       Object localObject1 = null;
/* 144 */       Object localObject2 = null;
/* 145 */       String str1 = localBufferedReader.readLine();
/* 146 */       int i = -1;
/* 147 */       if (str1 != null) {
/* 148 */         i = str1.indexOf('=');
/*     */       }
/* 150 */       while ((str1 != null) && (i != -1)) {
/* 151 */         String str2 = str1.substring(0, i);
/* 152 */         String str3 = str1.substring(i + 1);
/* 153 */         String str4 = str2.substring(0, str2.lastIndexOf('.'));
/*     */ 
/* 155 */         if ((localObject1 == null) || (!localObject1.equals(str4))) {
/* 156 */           if (localObject1 != null) {
/* 157 */             localHashtable.put(localObject1, localObject2);
/*     */           }
/* 159 */           localObject1 = str4;
/* 160 */           localObject2 = new Hashtable();
/*     */         }
/* 162 */         ((Hashtable)localObject2).put(str2, str3);
/*     */ 
/* 164 */         str1 = localBufferedReader.readLine();
/* 165 */         if (str1 != null) {
/* 166 */           i = str1.indexOf('=');
/*     */         }
/*     */       }
/* 169 */       if (localObject1 != null)
/* 170 */         localHashtable.put(localObject1, localObject2);
/*     */     }
/*     */     finally {
/* 173 */       if (localBufferedReader != null) {
/* 174 */         localBufferedReader.close();
/*     */       }
/*     */     }
/*     */ 
/* 178 */     return (Hashtable)localHashtable;
/*     */   }
/*     */ 
/*     */   public Hashtable getGroup(String paramString)
/*     */     throws IOException
/*     */   {
/*     */     URL localURL;
/*     */     try
/*     */     {
/* 191 */       localURL = new URL(this.url + "?action=list&group=" + paramString);
/*     */     } catch (MalformedURLException localMalformedURLException) {
/* 193 */       localMalformedURLException.printStackTrace(System.err);
/* 194 */       return null;
/*     */     }
/*     */ 
/* 197 */     Hashtable localHashtable = new Hashtable();
/* 198 */     BufferedReader localBufferedReader = null;
/*     */     try {
/* 200 */       localBufferedReader = new BufferedReader(new InputStreamReader(localURL.openStream()));
/*     */ 
/* 203 */       Object localObject1 = null;
/* 204 */       String str1 = localBufferedReader.readLine();
/* 205 */       int i = -1;
/* 206 */       if (str1 != null) {
/* 207 */         i = str1.indexOf('=');
/*     */       }
/* 209 */       while ((str1 != null) && (i != -1)) {
/* 210 */         String str2 = str1.substring(0, i);
/* 211 */         String str3 = str1.substring(i + 1);
/*     */ 
/* 213 */         localHashtable.put(str2, str3);
/*     */ 
/* 215 */         str1 = localBufferedReader.readLine();
/* 216 */         if (str1 != null)
/* 217 */           i = str1.indexOf('=');
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/* 222 */       if (localBufferedReader != null) {
/* 223 */         localBufferedReader.close();
/*     */       }
/*     */     }
/*     */ 
/* 227 */     return localHashtable;
/*     */   }
/*     */ 
/*     */   public void update(Hashtable paramHashtable)
/*     */     throws IOException
/*     */   {
/* 237 */     String str1 = "?action=update";
/* 238 */     Enumeration localEnumeration = paramHashtable.keys();
/*     */     Object localObject1;
/* 239 */     while (localEnumeration.hasMoreElements()) {
/* 240 */       localObject1 = (String)localEnumeration.nextElement();
/* 241 */       str1 = str1 + "&" + (String)localObject1 + "=" + URLEncoder.encode(paramHashtable.get(localObject1).toString(), defaultEncoding);
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 248 */       localObject1 = new URL(this.url + str1);
/*     */     } catch (MalformedURLException localMalformedURLException) {
/* 250 */       localMalformedURLException.printStackTrace(System.err);
/* 251 */       return;
/*     */     }
/*     */ 
/* 254 */     BufferedReader localBufferedReader = null;
/*     */     try {
/* 256 */       localBufferedReader = new BufferedReader(new InputStreamReader(((URL)localObject1).openStream()));
/*     */ 
/* 258 */       String str2 = localBufferedReader.readLine();
/* 259 */       if (!str2.equals("OK"))
/* 260 */         System.err.println("OOPS!!!: " + str2);
/*     */     }
/*     */     finally {
/* 263 */       if (localBufferedReader != null)
/* 264 */         localBufferedReader.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void removeGroup(String paramString)
/*     */     throws IOException
/*     */   {
/*     */     URL localURL;
/*     */     try
/*     */     {
/* 278 */       localURL = new URL(this.url + "?action=remove&group=" + paramString);
/*     */     } catch (MalformedURLException localMalformedURLException) {
/* 280 */       localMalformedURLException.printStackTrace(System.err);
/* 281 */       return;
/*     */     }
/*     */ 
/* 284 */     BufferedReader localBufferedReader = null;
/*     */     try {
/* 286 */       localBufferedReader = new BufferedReader(new InputStreamReader(localURL.openStream()));
/*     */ 
/* 288 */       String str = localBufferedReader.readLine();
/* 289 */       if (!str.equals("OK"))
/* 290 */         System.err.println("ParamTool OOPS: " + str);
/*     */     }
/*     */     finally {
/* 293 */       if (localBufferedReader != null)
/* 294 */         localBufferedReader.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public class TooManyGroupsException extends Exception
/*     */   {
/*     */     public TooManyGroupsException(String arg2)
/*     */     {
/*  35 */       super();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Developer\Desktop\ama.jar
 * Qualified Name:     ama.ParamTool
 * JD-Core Version:    0.6.0
 */