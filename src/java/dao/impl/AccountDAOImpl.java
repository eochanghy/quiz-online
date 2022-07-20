/*
 * Copyright(C) 2021, Nguyen Thanh Dat.
 * J3.L.P0001
 * Quiz Online
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2021-07-07      1.0                 DatNT           First Implement
 * 2021-07-13      2.0                 DatNT           Fix Comment 
 */
package dao.impl;

import context.DBContext;
import dao.AccountDAO;
import entity.Account;
import entity.QuestionSingle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndDocument;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;

/**
 * Contains method about account table to implement. Method getAccount: Gets
 * account by user name and password. Method createAccount: Inserts account to
 * account table. Method isExistedAccount: Checks account is existed in
 * database. Method getListPagingSearchResult: Gets list search result article
 * with paging.
 * <p>
 * Bugs: None
 *
 * @author Nguyen Thanh Dat
 */
public class AccountDAOImpl extends DBContext implements AccountDAO {

    /**
     * Gets Account by user name and password. The account that have user name
     * password correct with input will be returned. The result is a
     * <code>entity.Account</code> objects with id, name, password, type, email.
     *
     * @param name the user name of account. It is a
     * <code>java.lang.String</code> object
     * @param password the password of account. It is a
     * <code>java.lang.String</code> object
     * @return a <code>entity.Account</code> objects.
     * @throws java.lang.Exception
     */
    @Override
    public Account getAcount(String name, String password) throws Exception {
        List<Account> accounts = getAllAccount();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getName().equals(name) && accounts.get(i).getPassword().equals(password)) {
                return accounts.get(i);
            }
        }
        return null;
    }

    /**
     * Inserts account to database.
     *
     * @param a the account will be insert. It is a a
     * <code>entity.Account</code> object
     * @throws java.lang.Exception
     */
    @Override
    public void createAccount(Account a) throws Exception {      
        writeAccountXML(a, new File("D:/FPT_University/SUM2022/PRX301/Project/Lab01_Quiz/User.xml"));
    }

    private void writeAccountXML(Account acc, File file) {
        try {
            List<Account> accounts = getAllAccount();
            accounts.add(acc);

            XMLOutputFactory factory = XMLOutputFactory.newFactory();
            XMLEventWriter writer = factory.createXMLEventWriter(new FileOutputStream(file));
            XMLEventFactory ef = XMLEventFactory.newFactory();

            StartDocument sd = ef.createStartDocument("utf-8", "1.0");
            EndDocument ed = ef.createEndDocument();

            StartElement sUsers = ef.createStartElement("", "", "users");
            EndElement eUsers = ef.createEndElement("", "", "users");

            StartElement sUser = ef.createStartElement("", "", "user");
            EndElement eUser = ef.createEndElement("", "", "user");

            StartElement sType = ef.createStartElement("", "", "type");
            EndElement eType = ef.createEndElement("", "", "type");

            StartElement sUserName = ef.createStartElement("", "", "username");
            EndElement eUserName = ef.createEndElement("", "", "username");

            StartElement sPass = ef.createStartElement("", "", "password");
            EndElement ePass = ef.createEndElement("", "", "password");

            StartElement sEmail = ef.createStartElement("", "", "email");
            EndElement eEmail = ef.createEndElement("", "", "email");

            StartElement sAge = ef.createStartElement("", "", "age");
            EndElement eAge = ef.createEndElement("", "", "age");

            StartElement sClass = ef.createStartElement("", "", "class");
            EndElement eClass = ef.createEndElement("", "", "class");

            //add 
            writer.add(sd);
            writer.add(sUsers);
            for (Account a : accounts) {
                writer.add(sUser);
                Attribute id = ef.createAttribute("id", String.valueOf(a.getId()));
                writer.add(id);

                writer.add(sType);
                writer.add(ef.createCharacters(a.getType()));
                writer.add(eType);

                writer.add(sUserName);
                writer.add(ef.createCharacters(a.getName()));
                writer.add(eUserName);

                writer.add(sPass);
                writer.add(ef.createCharacters(a.getPassword()));
                writer.add(ePass);

                writer.add(sEmail);
                writer.add(ef.createCharacters(a.getEmail()));
                writer.add(eEmail);

                writer.add(sAge);
                writer.add(ef.createCharacters(String.valueOf(a.getAge())));
                writer.add(eAge);

                writer.add(sClass);
                writer.add(ef.createCharacters(a.getClassName()));
                writer.add(eClass);

                writer.add(eUser);
            }
            writer.add(eUsers);

            writer.add(ed);

            writer.flush();
            writer.close();
        } catch (FileNotFoundException | XMLStreamException ex) {
            Logger.getLogger(AccountDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AccountDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Checks account is existed in database. If account is existed, return true
     * else return false. The result is a <code>java.lang.Boolean</code>
     * objects.
     *
     * @param userName the user name of account. It is a
     * <code>java.lang.String</code> object
     * @return a <code>java.lang.Boolean</code> objects.
     * @throws java.lang.Exception
     */
    @Override
    public boolean isExistedAccount(String userName) throws Exception {
        List<Account> accounts = getAllAccount();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getName().equalsIgnoreCase(userName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Account> getAllAccount() throws Exception {
        List<Account> accounts = new ArrayList<>();
        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream("D:/FPT_University/SUM2022/PRX301/Project/Lab01_Quiz/User.xml"));
        Account account = new Account();
        while (reader.hasNext()) {
            int type = reader.next();
            if (type == XMLStreamReader.START_ELEMENT) {
                String name = reader.getName().toString();
                if (name.equals("user")) {
                    account = new Account();
                    String id = reader.getAttributeValue("", "id");
                    account.setId(Integer.parseInt(id));
                }
                if ("type".equals(name)) {
                    account.setType(reader.getElementText());
                }
                if ("username".equals(name)) {
                    account.setName(reader.getElementText());
                }
                if ("password".equals(name)) {
                    account.setPassword(reader.getElementText());
                }
                if ("email".equals(name)) {
                    account.setEmail(reader.getElementText());
                }
                if ("age".equals(name)) {
                    account.setAge(Integer.parseInt(reader.getElementText()));
                }
                if ("class".equals(name)) {
                    account.setClassName(reader.getElementText());
                }
            }
            if (type == XMLStreamReader.END_ELEMENT) {
                String name = reader.getName().toString();
                if ("user".equals(name)) {
                    accounts.add(account);
                }
            }
        }
        return accounts;
    }

}
