package com.sky.kay.bdoa.model;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class ReceiveMail {

    private MimeMessage msg = null;
    private String saveAttchPath = "";
    private StringBuffer bodytext = new StringBuffer();
    private String dateformate = "yy-MM-dd HH:mm";

    public ReceiveMail(MimeMessage msg) {
        this.msg = msg;
    }

    public void setMsg(MimeMessage msg) {
        this.msg = msg;
    }

    /**
     * 获取发送邮件者信息
     *
     * @return
     * @throws MessagingException
     */
    public String getFrom() throws MessagingException {
        InternetAddress[] address = (InternetAddress[]) msg.getFrom();
        String from = address[0].getAddress();
        if (from == null) {
            from = "";
        }
        String personal = address[0].getPersonal();
        if (personal == null) {
            personal = "";
        }
        String fromaddr = personal + "<" + from + ">";
        return fromaddr;
    }

    /**
     * 获取邮件收件人，抄送，密送的地址和信息。根据所传递的参数不同 "to"-->收件人,"cc"-->抄送人地址,"bcc"-->密送地址
     *
     * @param type
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public String getMailAddress(String type) throws MessagingException, UnsupportedEncodingException {
        String mailaddr = "";
        String addrType = type.toUpperCase();
        InternetAddress[] address = null;

        if (addrType.equals("TO") || addrType.equals("CC") || addrType.equals("BCC")) {
            if (addrType.equals("TO")) {
                address = (InternetAddress[]) msg.getRecipients(Message.RecipientType.TO);
            }
            if (addrType.equals("CC")) {
                address = (InternetAddress[]) msg.getRecipients(Message.RecipientType.CC);
            }
            if (addrType.equals("BCC")) {
                address = (InternetAddress[]) msg.getRecipients(Message.RecipientType.BCC);
            }

            if (address != null) {
                for (int i = 0; i < address.length; i++) {
                    String mail = address[i].getAddress();
                    if (mail == null) {
                        mail = "";
                    } else {
                        mail = MimeUtility.decodeText(mail);
                    }
                    String personal = address[i].getPersonal();
                    if (personal == null) {
                        personal = "";
                    } else {
                        personal = MimeUtility.decodeText(personal);
                    }
                    String compositeto = personal + "<" + mail + ">";
                    mailaddr += "," + compositeto;
                }
                mailaddr = mailaddr.substring(1);
            }
        } else {
            throw new RuntimeException("Error email Type!");
        }
        return mailaddr;
    }

    /**
     * 获取邮件主题
     *
     * @return
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    public String getSubject() throws UnsupportedEncodingException, MessagingException {
        String subject = "";
        subject = MimeUtility.decodeText(msg.getSubject());
        if (subject == null) {
            subject = "";
        }
        return subject;
    }

    /**
     * 获取邮件发送日期
     *
     * @return
     * @throws MessagingException
     */
    public String getSendDate() throws MessagingException {
        Date sentdata = msg.getSentDate();
        if (sentdata != null) {
            SimpleDateFormat format = new SimpleDateFormat(dateformate);
            return format.format(sentdata);
        } else {
            return "";
        }
    }

    /**
     * 获取邮件正文内容
     *
     * @return
     */
    public String getBodyText() {

        return bodytext.toString();
    }

    /**
     * 解析邮件，将得到的邮件内容保存到一个stringBuffer对象中，解析邮件 主要根据MimeType的不同执行不同的操作，一步一步的解析
     *
     * @param part
     * @throws MessagingException
     * @throws IOException
     */
    public void getMailContent(Part part) throws MessagingException, IOException {

        String contentType = part.getContentType();
        int nameindex = contentType.indexOf("name");
        boolean conname = false;
        if (nameindex != -1) {
            conname = true;
        }
        System.out.println("CONTENTTYPE:" + contentType);
        if (part.isMimeType("text/plain") && !conname) {
            bodytext.append((String) part.getContent());
        } else if (part.isMimeType("text/html") && !conname) {
            Object o =part.getContent();

        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            int count = multipart.getCount();
            for (int i = 0; i < count; i++) {
                getMailContent(multipart.getBodyPart(i));
            }
        } else if (part.isMimeType("message/rfc822")) {
            getMailContent((Part) part.getContent());
        }

    }

    /**
     * 判断邮件是否需要回执，如需回执返回true，否则返回false
     *
     * @return
     * @throws MessagingException
     */
    public boolean getReplySign() throws MessagingException {
        boolean replySign = false;
        String needreply[] = msg.getHeader("Disposition-Notification-TO");
        if (needreply != null) {
            replySign = true;
        }
        return replySign;
    }

    /**
     * 获取此邮件的message-id
     *
     * @return
     * @throws MessagingException
     */
    public String getMessageId() throws MessagingException {
        return msg.getMessageID();
    }

    /**
     * 判断此邮件是否已读，如果未读则返回false，已读返回true
     *
     * @return
     * @throws MessagingException
     */
    public boolean isNew() throws MessagingException {
        boolean isnew = false;
        Flags flags = ((Message) msg).getFlags();
        Flags.Flag[] flag = flags.getSystemFlags();
        System.out.println("flags's length:" + flag.length);
        for (int i = 0; i < flag.length; i++) {
            if (flag[i] == Flags.Flag.SEEN) {
                isnew = true;
                System.out.println("seen message .......");
                break;
            }
        }

        return isnew;
    }

    /**
     * 判断是是否包含附件
     *
     * @param part
     * @return
     * @throws MessagingException
     * @throws IOException
     */
    public boolean isContainAttch(Part part) throws MessagingException, IOException {
        boolean flag = false;

        String contentType = part.getContentType();
        if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            int count = multipart.getCount();
            for (int i = 0; i < count; i++) {
                BodyPart bodypart = multipart.getBodyPart(i);
                String dispostion = bodypart.getDisposition();
                if ((dispostion != null) && (dispostion.equals(Part.ATTACHMENT) || dispostion.equals(Part.INLINE))) {
                    flag = true;
                } else if (bodypart.isMimeType("multipart/*")) {
                    flag = isContainAttch(bodypart);
                } else {
                    String conType = bodypart.getContentType();
                    if (conType.toLowerCase().indexOf("appliaction") != -1) {
                        flag = true;
                    }
                    if (conType.toLowerCase().indexOf("name") != -1) {
                        flag = true;
                    }
                }
            }
        } else if (part.isMimeType("message/rfc822")) {
            flag = isContainAttch((Part) part.getContent());
        }

        return flag;
    }

    /**
     * 保存附件
     *
     * @param part
     * @throws MessagingException
     * @throws IOException
     */
    public void saveAttchMent(Part part) throws MessagingException, IOException {
        String filename = "";
        if (part.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) part.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                BodyPart mpart = mp.getBodyPart(i);
                String dispostion = mpart.getDisposition();
                if ((dispostion != null) && (dispostion.equals(Part.ATTACHMENT) || dispostion.equals(Part.INLINE))) {
                    filename = mpart.getFileName();
                    if (filename.toLowerCase().indexOf("gb2312") != -1) {
                        filename = MimeUtility.decodeText(filename);
                    }
                    saveFile(filename, mpart.getInputStream());
                } else if (mpart.isMimeType("multipart/*")) {
                    saveAttchMent(mpart);
                } else {
                    filename = mpart.getFileName();
                    if (filename != null && (filename.toLowerCase().indexOf("gb2312") != -1)) {
                        filename = MimeUtility.decodeText(filename);
                    }
                    saveFile(filename, mpart.getInputStream());
                }
            }

        } else if (part.isMimeType("message/rfc822")) {
            saveAttchMent((Part) part.getContent());
        }
    }

    /**
     * 获得保存附件的地址
     *
     * @return
     */
    public String getSaveAttchPath() {
        return saveAttchPath;
    }

    /**
     * 设置保存附件地址
     *
     * @param saveAttchPath
     */
    public void setSaveAttchPath(String saveAttchPath) {
        this.saveAttchPath = saveAttchPath;
    }

    /**
     * 设置日期格式
     *
     * @param dateformate
     */
    public void setDateformate(String dateformate) {
        this.dateformate = dateformate;
    }

    /**
     * 保存文件内容
     *
     * @param filename
     * @param inputStream
     * @throws IOException
     */
    private void saveFile(String filename, InputStream inputStream) throws IOException {
        String osname = System.getProperty("os.name");
        String storedir = getSaveAttchPath();
        String sepatror = "";
        if (osname == null) {
            osname = "";
        }

        if (osname.toLowerCase().indexOf("win") != -1) {
            sepatror = "//";
            if (storedir == null || "".equals(storedir)) {
                storedir = "d://temp";
            }
        } else {
            sepatror = "/";
            storedir = "/temp";
        }

        File storefile = new File(storedir + sepatror + filename);
        System.out.println("storefile's path:" + storefile.toString());

        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;

        try {
            bos = new BufferedOutputStream(new FileOutputStream(storefile));
            bis = new BufferedInputStream(inputStream);
            int c;
            while ((c = bis.read()) != -1) {
                bos.write(c);
                bos.flush();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            bos.close();
            bis.close();
        }

    }

    public void recive(Part part,int i) throws MessagingException, IOException{
        Log.e("AAA",getSubject());
        Log.e("AAA","Message"+i+" from:" + getFrom());
        Log.e("AAA","Message"+i+" isNew:" + isNew());
        Log.e("AAA","Message"+i+" replySign:" + getReplySign());
        Log.e("AAA","Message"+i+" content:" + getBodyText());

    }

    public void mailReceiver(Message msg)throws Exception{
        if(msg.getContent() instanceof Multipart) {
            Multipart multipart = (Multipart) msg.getContent() ;
            reMultipart(multipart);
        } else if (msg.getContent() instanceof Part){
            Part part = (Part) msg.getContent();
            rePart(part);
        } else {
            Log.e("AAA","类型" + msg.getContentType());
            Log.e("AAA","内容" + msg.getContent());
        }
    }

    /**
     * @param part 解析内容
     * @throws Exception
     */
    private void rePart(Part part) throws MessagingException,
            UnsupportedEncodingException, IOException, FileNotFoundException {
        if (part.getDisposition() != null) {

            String strFileNmae = MimeUtility.decodeText(part.getFileName()); //MimeUtility.decodeText解决附件名乱码问题
            Log.e("AAA","发现附件: " +  MimeUtility.decodeText(part.getFileName()));
            Log.e("AAA","内容类型: " + MimeUtility.decodeText(part.getContentType()));
            Log.e("AAA","附件内容:" + part.getContent());
            InputStream in = part.getInputStream();// 打开附件的输入流
            // 读取附件字节并存储到文件中
            java.io.FileOutputStream out = new FileOutputStream(strFileNmae);
            int data;
            while((data = in.read()) != -1) {
                out.write(data);
            }
            in.close();
            out.close();
        } else {
            if(part.getContentType().startsWith("text/plain")) {
                Log.e("AAA","文本内容：" + part.getContent());
            } else {
                //System.out.println("HTML内容：" + part.getContent());
            }
        }
    }

    /**
     * @param multipart // 接卸包裹（含所有邮件内容(包裹+正文+附件)）
     * @throws Exception
     */
    private void reMultipart(Multipart multipart) throws Exception {
        //System.out.println("邮件共有" + multipart.getCount() + "部分组成");
        // 依次处理各个部分
        for (int j = 0, n = multipart.getCount(); j < n; j++) {
            //System.out.println("处理第" + j + "部分");
            Part part = multipart.getBodyPart(j);//解包, 取出 MultiPart的各个部分, 每部分可能是邮件内容,
            // 也可能是另一个小包裹(MultipPart)
            // 判断此包裹内容是不是一个小包裹, 一般这一部分是 正文 Content-Type: multipart/alternative
            if (part.getContent() instanceof Multipart) {
                Multipart p = (Multipart) part.getContent();// 转成小包裹
                //递归迭代
                reMultipart(p);
            } else {
                rePart(part);
            }
        }
    }

    public void readContent(MimeMessage m)
            throws IOException, MessagingException{
        Object o = m.getContent();
        if (o instanceof String) {
            System.out.println(
                    "**This is a String Message**");
            System.out.println((String)o);
        }
        else if (o instanceof Multipart) {
            System.out.print(
                    "**This is a Multipart Message.  ");
            Multipart mp = (Multipart)o;
            int count3 = mp.getCount();
            System.out.println("It has " + count3 +
                    " BodyParts in it**");
            for (int j = 0; j < count3; j++) {
                // Part are numbered starting at 0
                BodyPart b = mp.getBodyPart(j);
                String mimeType2 = b.getContentType();
                System.out.println( "BodyPart " +
                        (j + 1) +
                        " is of MimeType " +
                        m.getContentType());

                Object o2 = b.getContent();
                if (o2 instanceof String) {
                    System.out.println(
                            "**This is a String BodyPart**");
                    System.out.println((String)o2);
                }
                else if (o2 instanceof Multipart) {
                    System.out.print(
                            "**This BodyPart is a nested Multipart.");
                    Multipart mp2 = (Multipart)o2;
                    int count2 = mp2.getCount();
                    System.out.println("It has " + count2 +
                            "further BodyParts in it**");
                }
                else if (o2 instanceof InputStream) {
                    System.out.println(
                            "**This is an InputStream BodyPart**");
                }
            } //End of for
        }
        else if (o instanceof InputStream) {
            System.out.println("**This is an InputStream message**");
            InputStream is = (InputStream)o;
            // Assumes character content (not binary images)
            int c;
            while ((c = is.read()) != -1) {
                System.out.write(c);
            }
        }
    }


}
    