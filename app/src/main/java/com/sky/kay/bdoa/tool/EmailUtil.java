package com.sky.kay.bdoa.tool;

import android.app.Activity;
import android.content.Context;

import com.sky.kay.bdoa.model.App;
import com.sky.kay.bdoa.model.Mail;
import com.sky.kay.bdoa.model.ReceiveMail;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;


/**
 * Created by kay on 2016/7/12.
 */
public class EmailUtil {
    private Context context;
    private App app;
    private SimpleDateFormat sdf;
    private ArrayList<Mail> listMail=new ArrayList<Mail>();
    public EmailUtil(Activity context) {

        this.context = context;
        app = (App) context.getApplication();
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    public void getImapEmail() {
//        String user = "abcw111222@163.com";// 邮箱的用户名
//        String password = "123456w"; // 邮箱的密码
        String user = "chenguohui@gdbds.net";// 邮箱的用户名
        String password = "ZhangSan520"; // 邮箱的密码
        Properties prop = System.getProperties();
        prop.put("mail.store.protocol", "imap");
        prop.put("mail.imap.host", "imap.exmail.qq.com");

        Session session = Session.getInstance(prop);

        int total = 0;
        IMAPStore store;
        try {
            store = (IMAPStore) session.getStore("imap"); // 使用imap会话机制，连接服务器

            store.connect(user, password);

            IMAPFolder folder = (IMAPFolder) store.getFolder("INBOX"); // 收件箱
            folder.open(Folder.READ_WRITE);
            // 获取总邮件数
            total = folder.getMessageCount();
            System.out.println("---共有邮件：" + total + " 封---");
            // 得到收件箱文件夹信息，获取邮件列表
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            System.out.println("未读邮件数：" + folder.getUnreadMessageCount());
            Message[] messages = folder.getMessages();

            if (messages.length > 0) {
                ReceiveMail rm = null;

                for (int i = messages.length-1; i >=0; i--) {
                    Message m = messages[i];
                    ReceiveMail receiveMail =new ReceiveMail((MimeMessage)m);

                    Multipart multipart= (Multipart) m.getContent();
                    int n=multipart.getCount();
                    for(int j=0;j<n;j++){
                        Part part=multipart.getBodyPart(j);
                        receiveMail.getMailContent(part);
                    }
                    Mail mail=new Mail();
                    receiveMail.mailReceiver(m);
                    mail.date= receiveMail.getSendDate();
                    mail.title = receiveMail.getSubject();
                    mail.sender= receiveMail.getFrom();
                    listMail.add(mail);
                }
                app.mails=listMail;
                app.messages=messages;
                folder.close(true);
                store.close();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {

        }


    }



    public void getEmail() {

        String host = "pop.exmail.qq.com";
        String user = "chenguohui@gdbds.net";
        String password = "ZhangSan520";

        String subjectSubstringToSearch = "Test E-Mail through Java";


        Session session = Session.getInstance(new Properties());


        try {

            Store store = session.getStore("pop3");

            store.connect(host, user, password);


            Folder fldr = store.getFolder("INBOX");

            fldr.open(Folder.READ_WRITE);

            int count = fldr.getMessageCount();

            System.out.println(count + " total messages");


            // Message numbers start at 1
            ArrayList<Mail> mails = new ArrayList<Mail>();

            for (int i = 1; i <= count; i++) {

                // Get  a message by its sequence number

                Message m = fldr.getMessage(i);
                Mail mail = new Mail();
                Date date = m.getSentDate();
                mail.date = sdf.format(date);
                Address[] from = m.getFrom();

                String subj = m.getSubject();
                mail.title = subj;
                String mimeType = m.getContentType();

                readContent(m);

            }
            fldr.close(true);
            store.close();
        } catch (Exception e) {

        } finally {

        }
    }

    public static void readContent(Message m)

            throws IOException, MessagingException {

        Object o = m.getContent();

        if (o instanceof String) {


            System.out.println((String) o);

        } else if (o instanceof Multipart) {


            Multipart mp = (Multipart) o;

            int count3 = mp.getCount();


            for (int j = 0; j < count3; j++) {


                BodyPart b = mp.getBodyPart(j);

                String mimeType2 = b.getContentType();


                Object o2 = b.getContent();

                if (o2 instanceof String) {


                    System.out.println((String) o2);

                } else if (o2 instanceof Multipart) {

                    System.out.print(

                            "**This BodyPart is a nested Multipart.");

                    Multipart mp2 = (Multipart) o2;

                    int count2 = mp2.getCount();

                    System.out.println("It has " + count2 +

                            "further BodyParts in it**");

                } else if (o2 instanceof InputStream) {

                    System.out.println(

                            "**This is an InputStream BodyPart**");

                }

            } //End of for

        } else if (o instanceof InputStream) {

            System.out.println("**This is an InputStream message**");

            InputStream is = (InputStream) o;

            // Assumes character content (not binary images)

            int c;

            while ((c = is.read()) != -1) {

                System.out.write(c);

            }


        }

    }

}
