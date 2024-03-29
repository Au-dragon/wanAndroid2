package com.example.xts.greendaodemo.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.oujunlong.wanandroid2.bean.DaoBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DAO_BEAN".
*/
public class DaoBeanDao extends AbstractDao<DaoBean, Long> {

    public static final String TABLENAME = "DAO_BEAN";

    /**
     * Properties of entity DaoBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Author = new Property(1, String.class, "author", false, "AUTHOR");
        public final static Property SuperChapterName = new Property(2, String.class, "superChapterName", false, "SUPER_CHAPTER_NAME");
        public final static Property Title = new Property(3, String.class, "title", false, "TITLE");
        public final static Property NiceDate = new Property(4, String.class, "niceDate", false, "NICE_DATE");
    }


    public DaoBeanDao(DaoConfig config) {
        super(config);
    }
    
    public DaoBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DAO_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"AUTHOR\" TEXT," + // 1: author
                "\"SUPER_CHAPTER_NAME\" TEXT," + // 2: superChapterName
                "\"TITLE\" TEXT," + // 3: title
                "\"NICE_DATE\" TEXT);"); // 4: niceDate
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DAO_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DaoBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(2, author);
        }
 
        String superChapterName = entity.getSuperChapterName();
        if (superChapterName != null) {
            stmt.bindString(3, superChapterName);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }
 
        String niceDate = entity.getNiceDate();
        if (niceDate != null) {
            stmt.bindString(5, niceDate);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DaoBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(2, author);
        }
 
        String superChapterName = entity.getSuperChapterName();
        if (superChapterName != null) {
            stmt.bindString(3, superChapterName);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }
 
        String niceDate = entity.getNiceDate();
        if (niceDate != null) {
            stmt.bindString(5, niceDate);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DaoBean readEntity(Cursor cursor, int offset) {
        DaoBean entity = new DaoBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // author
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // superChapterName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // title
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // niceDate
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DaoBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAuthor(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSuperChapterName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTitle(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setNiceDate(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DaoBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DaoBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DaoBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
