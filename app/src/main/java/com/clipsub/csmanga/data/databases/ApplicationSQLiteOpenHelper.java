package com.clipsub.csmanga.data.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.clipsub.csmanga.models.Chapter;
import com.clipsub.csmanga.models.DownloadChapter;
import com.clipsub.csmanga.models.DownloadManga;
import com.clipsub.csmanga.models.DownloadPage;
import com.clipsub.csmanga.models.FavoriteManga;
import com.clipsub.csmanga.models.RecentChapter;

import nl.qbusict.cupboard.Cupboard;
import nl.qbusict.cupboard.CupboardBuilder;

public class ApplicationSQLiteOpenHelper extends SQLiteOpenHelper {

  public static final String TAG = ApplicationSQLiteOpenHelper.class.getSimpleName();

  public ApplicationSQLiteOpenHelper(Context context) {
    super(context, ApplicationContract.DATABASE_NAME, null, ApplicationContract.DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    Cupboard applicationCupboard = constructCustomCupboard();
    applicationCupboard.withDatabase(db).createTables();
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Cupboard applicationCupboard = constructCustomCupboard();
    applicationCupboard.withDatabase(db).upgradeTables();
  }

  /**
   * Construct custom Cupboard instance.
   *
   * @return Custom Cupboard instance for Application SQLite instance.
   */
  private Cupboard constructCustomCupboard() {
    Cupboard customCupboard = new CupboardBuilder().build();
    customCupboard.register(Chapter.class);
    customCupboard.register(FavoriteManga.class);
    customCupboard.register(RecentChapter.class);
    customCupboard.register(DownloadManga.class);
    customCupboard.register(DownloadChapter.class);
    customCupboard.register(DownloadPage.class);

    return customCupboard;
  }
}
