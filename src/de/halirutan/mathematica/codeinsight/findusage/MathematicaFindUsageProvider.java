/*
 * Copyright (c) 2014 Patrick Scheibe
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package de.halirutan.mathematica.codeinsight.findusage;

import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import de.halirutan.mathematica.lexer.MathematicaLexer;
import de.halirutan.mathematica.parsing.MathematicaElementTypes;
import de.halirutan.mathematica.parsing.psi.api.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author patrick (7/6/14)
 */
public class MathematicaFindUsageProvider implements FindUsagesProvider {
  @Nullable
  @Override
  public WordsScanner getWordsScanner() {
    return new DefaultWordsScanner(new MathematicaLexer(),
        TokenSet.create(MathematicaElementTypes.IDENTIFIER),
        MathematicaElementTypes.COMMENTS,
        MathematicaElementTypes.LITERALS);
  }

  @Override
  public boolean canFindUsagesFor(@NotNull final PsiElement psiElement) {
    return psiElement instanceof Symbol;
  }

  @Nullable
  @Override
  public String getHelpId(@NotNull final PsiElement psiElement) {
    return null;
  }

  @NotNull
  @Override
  public String getType(@NotNull final PsiElement element) {
    return "Symbol";
  }

  @NotNull
  @Override
  public String getDescriptiveName(@NotNull final PsiElement element) {
    if (element instanceof Symbol) {
      return ((Symbol) element).getMathematicaContext() + ((Symbol) element).getSymbolName();
    }
    return "";
  }

  @NotNull
  @Override
  public String getNodeText(@NotNull final PsiElement element, final boolean useFullName) {
    return element.getText();
  }
}
